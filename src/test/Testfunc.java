package test;

import base.syntree.*;

public class Testfunc {
    public static void main(String[] args) {
        Head c = new Head(2,new Head(4,2,Op.MINUS),Op.PLUS);
        TreeTraverse t = new TreeTraverse(c);
        t.toRight();
        t.toParent();
        System.out.println(t.getCurrent());
    }

}