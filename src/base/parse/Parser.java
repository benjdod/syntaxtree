package base.parse;

import java.util.ArrayList;
import java.util.Iterator;

import base.syntree.Branch;
import base.syntree.Head;
import base.syntree.Op;
import base.syntree.TreeCreator;
import base.token.TokenIterator;

public class Parser {
    public static Head parse(ArrayList<String> strs) {
        // GLOBAL VARIABLES:
        Head current = new Head(new Branch());
        TreeCreator t = new TreeCreator(current);
        TokenIterator i = new TokenIterator(strs);
        // LOOP VARIABLES:
        String s;
        // (3+4*2)
        // one level session
        t.getCurrent().getBranch().setLeft(Double.parseDouble(i.next()));
        t.getCurrent().getBranch().setOperator(charToOp(i.next().charAt(0)));
        t.getCurrent().getBranch().setRight(Double.parseDouble(i.next()));
        while (i.hasTwoNext()) {
            if (getOpRank(i.next().charAt(0)) < getOpRank(current.getBranch().getOp())) {
                t.subLeft();
                t.toRight();
                t.setOperator(charToOp(i.current().charAt(0)));
                t.setRight(Double.parseDouble(i.next()));
                t.set(current);
            } else {
                t.superLeft();
                t.setOperator(charToOp(i.current().charAt(0)));
                t.setRight(Double.parseDouble(i.next()));
            }
        }
        
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