package test;

import java.util.ArrayList;
import base.syntree.*;
import base.token.Tokenizer;

public class Testfunc {
    public static void main(String[] args) {
        Child c = new Child(new Branch(new Branch(3,2,Op.MINUS),2,Op.DIV));
        TreeTraverse t = new TreeTraverse(c);
        Child d = c.getBranch().getLeftChild().getParent();
        System.out.println(c == d);
    }

}