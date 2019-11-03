package test;

import base.syntree.*;

public class ExampleTest {
    public static void main(String[] args) {
        Head c = new Head(2,new Head(new Head(3,2,Op.DIV),2,Op.MINUS),Op.PLUS);
        System.out.println("-----Tree functionality-----");
        System.out.println("c: " + c.toString());
        System.out.println("c calculated: " + c.calculate());

        TreeTraverse t = new TreeTraverse(c);
        System.out.println("\n-----Tree Traversal----- \nt = TreeTraverse(c)");
        System.out.println("t.getCurrent(): " + t.getCurrent());
        t.toRight();
        System.out.println("toRight() -> " + t.getCurrent());
        t.toLeft();
        System.out.println("toLeft() -> " + t.getCurrent());
        t.toLeft();
        System.out.println("toLeft() -> " + t.getCurrent());

        
    }

}