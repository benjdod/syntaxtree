package test;

import base.syntree.TreeCreator;
import base.syntree.Head;
import base.syntree.Op;

public class TreeCreatorTest {
    public static void main(String[] args) {
        TreeCreator t = new TreeCreator();
        t.setLeft(3.2);
        t.setRight(4.5);
        t.setOperator(Op.EXP);
        t.subLeft();
        t.toRight();
        //t.toParent();
        System.out.println(t.getCurrent());
    }
}