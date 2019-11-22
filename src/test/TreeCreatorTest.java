package test;

import base.tree.TreeCreator;
import base.tree.struct.Op;

public class TreeCreatorTest {
    public static void main(String[] args) {
        TreeCreator t = new TreeCreator();
        t.setLeft(3.2);
        t.setRight(4.5);
        t.setOperator(Op.EXP);
        t.subLeft();
        t.toRight();
        t.setOperator(Op.DIV);
        t.setRight(7.2);
        t.superLeft();
        t.toParent();
        System.out.println(t.getCurrent());
    }
}