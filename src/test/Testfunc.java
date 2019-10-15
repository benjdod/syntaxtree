package test;

import java.util.ArrayList;
import base.syntree.*;
import base.token.Tokenizer;

public class Testfunc {
    public static void main(String[] args) {
        Child c = new Child(3,5,Op.MINUS);
        c.getBranch().setLeft(new Branch(3,4,Op.PLUS));
        System.out.println(c.getBranch().calculate());
    }

}