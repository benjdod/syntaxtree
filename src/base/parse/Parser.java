package base.parse;

import java.util.ArrayList;

import base.parse.SessionHead.SessionState;
import base.tree.struct.Head;
import base.tree.struct.Op;
import base.tree.TreeCreator;
import base.parse.helper.TokenIterator;;

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
            if (i.current().equals("(")) {
                if (current.getState() == SessionState.LEFT) {
                    current.setState(SessionState.OP);
                    SessionHead tmp = current;
                    current = new SessionHead(tmp);
                    current.setParent(tmp); 
                    t.setLeft(current);
                    t.toLeft();
                    continue;
                } else if (current.getState() == SessionState.RIGHT) {
                    current.setState(SessionState.EVALOP);
                    SessionHead tmp = current;
                    current = new SessionHead(tmp);
                    current.setParent(tmp); 
                    t.setRight(current);
                    t.toRight();
                    continue;
                } else if (current.getState() == SessionState.EVALDIGIT) {
                    current.setState(SessionState.EVALOP);
                    SessionHead tmp = current;
                    current = new SessionHead(tmp);
                    current.setParent(tmp); 
                    t.setRight(current);
                    t.toRight();
                    continue;
                }
                
            }

            // ")" closes the session
            if (i.current().equals(")")) {
                SessionHead tmp = current;
                current = tmp.getSessionParent();
                t.set(current);
                continue;
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
        
        // ascend to the top once we're done
        while (t.getCurrent().hasParent()) {
            t.toParent();
        }

        return t.getCurrent();
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