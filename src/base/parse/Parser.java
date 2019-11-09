package base.parse;

import java.util.ArrayList;

import base.syntree.Branch;
import base.syntree.Head;
import base.syntree.Op;
import base.syntree.TreeModifier;

public class Parser {
    public static Head parse(ArrayList<String> s) {
        Head current;
        TreeModifier t;
        // (3+4*2)
        //#region
        // (3+4*2)
        // (: a new head w/empty branch
        // 3: fill left 
        // +: fill op
        // 4: fill right
        // *: evaluate against +, replace 4 w/new branch, fill left w/4,   
        //       fill op w/ *
        // 2: fill right w/2
        // ): end current head session (the whole evaluating precedence based
        //      on one ref head?)
        //#endregion
        current = new Head(new Branch());
        t = new TreeModifier(current);
        t.setLeft(new Head(3));
        t.setOperator(Op.PLUS);
        t.setRight(new Head(4));
        if (getOpRank('*')<getOpRank('+')) {
            // t.subBranchRight
        } else {
            // t.superBranch
        }
        // 
        // TODO: create a method to sub-branch
        // as in create a way for t to move the digit
        // into the left head of the new sub-branch and move into it. 
        System.out.println(t.getCurrent());
        return null;
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

    private static double toDigit(String s) {
        if (!isDigit(s)) {throw new IllegalArgumentException("provided string " + s + " is not a digit");}
        return Double.parseDouble(s);
    }

    private static int getOpRank(char c) {
        if (c == '^') {return 0;}
        if (c == '*') {return 1;}
        if (c == '/') {return 2;}
        if (c == '+') {return 3;}
        if (c == '-') {return 4;}
        throw new IllegalArgumentException("provided character is not a valid operator");
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