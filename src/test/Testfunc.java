package test;

import java.util.ArrayList;
import base.syntree.*;
import base.token.Tokenizer;

public class Testfunc {
    public static void main(String[] args) {
        // if you debug, you'll see these are different objects
        // this is likely due to constructor issues
        Branch b = new Branch(new Branch(7,new Branch(4,1,Op.PLUS),Op.MINUS),2,Op.DIV);
        Child c = b.getThisChild();
        Child d = b.getLeftChild().getParent();
        Child e = b.getLeftChild().getBranch().getRightChild();
        Child f = e.getParent();
        Child g = f.getParent();
        Child t = b.getLeftChild();
        System.out.println(c == d);
        System.out.println(b.getLeftChild() == f);
        System.out.println(c == e);
    }

}