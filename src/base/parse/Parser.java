package base.parse;

import java.util.ArrayList;
import java.util.Iterator;

import base.parse.SessionHead.SessionState;
import base.syntree.Branch;
import base.syntree.Head;
import base.syntree.Op;
import base.syntree.TreeCreator;
import base.token.TokenIterator;

public class Parser {

    // this parser operates on the assumption that every expression in one level of parentheses 
    // must follow the form EXP - OP - EXP - OP - EXP and so on. 

    public static Head parse(ArrayList<String> strs) {
        // GLOBAL VARIABLES:
        SessionHead current = new SessionHead();
        TreeCreator t = new TreeCreator(current);
        TokenIterator i = new TokenIterator(strs);
        // (3+4*2)
        while (i.hasNext()) {
            // advance to the next token
            i.next();

            // "(" opens a new session
            if (i.current() == "(") {
                SessionHead tmp = current;
                current = new SessionHead(tmp);
                // current.setState(SessionState.LEFT);
            }

            // ")" closes the session
            if (i.current() == ")") {
                SessionHead tmp = current;
                current = tmp.getParent();
            }

            if (current.getState() == SessionState.LEFT) {
                t.getCurrent().getBranch().setLeft(Double.parseDouble(i.current()));
                current.setState(SessionState.OP);
                continue;
            } else if (current.getState() == SessionState.OP) {
                t.getCurrent().getBranch().setOperator(charToOp(i.current().charAt(0)));
                current.setState(SessionState.RIGHT);
                continue;
            } else if (current.getState() == SessionState.RIGHT) {
                t.getCurrent().getBranch().setRight(Double.parseDouble(i.current()));
                current.setState(SessionState.EVALOP);
                continue;
            } else if (current.getState() == SessionState.EVALOP) {

                if (getOpRank(i.current().charAt(0)) < getOpRank(current.getBranch().getOp())) {
                    t.subLeft();
                    t.toRight();
                    t.setOperator(charToOp(i.current().charAt(0)));
                } else {
                    t.superLeft();
                    t.setOperator(charToOp(i.current().charAt(0)));
                }
                current.setState(SessionState.EVALDIGIT);
                continue;

            } else if (current.getState() == SessionState.EVALDIGIT) {
                t.getCurrent().getBranch().setRight(Double.parseDouble(i.current()));
                current.setState(SessionState.EVALOP);
                continue;
            } else {
                // the loop should never reach this point
            }

        }

        // t.getCurrent().getBranch().setLeft(Double.parseDouble(i.next()));
        // t.getCurrent().getBranch().setOperator(charToOp(i.next().charAt(0)));
        // t.getCurrent().getBranch().setRight(Double.parseDouble(i.next()));
        // while (i.hasTwoNext()) {
        //     if (getOpRank(i.next().charAt(0)) < getOpRank(current.getBranch().getOp())) {
        //         t.subLeft();
        //         t.toRight();
        //         t.setOperator(charToOp(i.current().charAt(0)));
        //         t.setRight(Double.parseDouble(i.next()));
        //         t.set((Head)current);
        //     } else {
        //         t.superLeft();
        //         t.setOperator(charToOp(i.current().charAt(0)));
        //         t.setRight(Double.parseDouble(i.next()));
        //     }
        // }
        
        // ascent to top
        while (t.getCurrent().hasParent()) {
            t.toParent();
        }

        return t.getCurrent();
    }

    private static boolean isDigit(String s) {
        boolean out = true;
        for (int i=0;i<s.length();i++) {
            if (((int)s.charAt(i) >= 48 && (int)s.charAt(i) <= 57) || (int)s.charAt(i) == 46) {
                continue;
            } else {
                out = false;
                break;
            }
        }
        return out;
    }

    private static boolean isOp(String s) {
        if (s.length() > 1) {return false;}
        char c = s.charAt(0);
        if (c == '^' || c == '*' || c == '/' || c == '+' || c == '-') {return true;}
        return false;
    }

    private static double toDigit(String s) {
        if (!isDigit(s)) {throw new IllegalArgumentException("provided string " + s + " is not a digit");}
        return Double.parseDouble(s);
    }

    private static int getOpRank(char c) {
        return getOpRank(charToOp(c));
    }

    private static int getOpRank (Op o) {
        if (o == Op.EXP) {return 0;}
        if (o == Op.MULT) {return 1;}
        if (o == Op.DIV) {return 2;}
        if (o == Op.PLUS) {return 3;}
        if (o == Op.MINUS) {return 4;}
        throw new IllegalArgumentException("provided Op is not a valid operator");
    }

    private static Op charToOp(char c) {
        if (c == '^') {return Op.EXP;}
        if (c == '*') {return Op.MULT;}
        if (c == '/') {return Op.DIV;}
        if (c == '+') {return Op.PLUS;}
        if (c == '-') {return Op.MINUS;}
        throw new IllegalArgumentException("provided character is not a valid operator");
    }
}