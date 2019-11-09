package test;

import base.syntree.Branch;
import base.syntree.Head;
import base.syntree.Op;
import base.syntree.TreeModifier;

public class SyntreeTest {
    public static void main(String[] args) {
        Head h = new Head(new Head(3,4,Op.PLUS),9,Op.MINUS);
        TreeModifier t = new TreeModifier(h);
        t.toLeft();
        t.setLeft(new Head(new Branch()));
        t.toParent();
        
        System.out.println(t.getCurrent().calculate());
    }
}