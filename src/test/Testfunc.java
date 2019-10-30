package test;

import base.syntree.*;

public class Testfunc {
    public static void main(String[] args) {
        Head c = new Head(2,new Head(new Head(3,2,Op.DIV),2,Op.MINUS),Op.PLUS);
        TreeTraverse t = new TreeTraverse(c);
        t.toRight();
        t.toLeft();
        t.toLeft();
        System.out.println(t.getCurrent());
    }

}