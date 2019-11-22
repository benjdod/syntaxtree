package test;

import base.tree.struct.*;

import java.util.ArrayList;

import base.parse.Lexer;
import base.parse.Parser;
import base.tree.TreeTraverse;

public class ExampleTest {

    // Demonstrates some of the project classes

    public static void main(String[] args) {
        Head c = new Head(2,new Head(new Head(3,2,Op.DIV),2,Op.MINUS),Op.PLUS);
        System.out.println("-----Tree Functionality-----");
        System.out.println("c: " + c.toString());
        System.out.println("c calculated: " + c.calculate());

        TreeTraverse t = new TreeTraverse(c);
        System.out.println("\n-----Tree Traversal----- \nt = TreeTraverse(c)");
        System.out.println("t.getCurrent(): " + t.getCurrent());
        t.toRight();
        System.out.println("toRight() -> " + t.getCurrent());
        t.toLeft();
        System.out.println("toLeft() -> " + t.getCurrent());

        String eval = "8.2*(4-2)^2";
        System.out.println("\n-----Lexing and Parsing-----\nInput string:\n" + eval );
        System.out.println("After lexing:");
        ArrayList<String> tokens = Lexer.tokens(eval);
        for (String s : tokens) {
            System.out.print(s + "  ");
        }
        System.out.println("\nAfter parsing:");
        Head h = Parser.parse(tokens);
        System.out.println(h);
        System.out.println("After Evaluating:");
        System.out.println(h.calculate());
    }

}