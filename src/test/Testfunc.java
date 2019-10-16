package test;

import java.util.ArrayList;
import base.syntree.*;
import base.token.Tokenizer;

public class Testfunc {
    public static void main(String[] args) {
        // if you debug, you'll see these are different objects
        // this is likely due to constructor issues
        Branch b = new Branch(2,3,Op.PLUS);
        Branch c = new Branch(5,4,Op.MINUS);
        b.setRight(c);
        TreeTraverse t = new TreeTraverse(b.getThisChild());
        t.toRight();
        t.getCurrent();
        t.toParent();
    }

}