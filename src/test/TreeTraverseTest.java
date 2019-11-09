package test;

import base.syntree.TreeTraverse;
import base.syntree.TreeModifier;
import base.syntree.Branch;
import base.syntree.Head;
import base.syntree.Op;
import base.syntree.TreeCreator;;

public class TreeTraverseTest {
    public static void main(String[] args) {
        Head h = new Head(new Head(new Branch(9,3,Op.MINUS)),new Head(new Branch(5,2,Op.PLUS)),Op.DIV);
        TreeCreator t = new TreeCreator(h);
        t.toLeft();
        System.out.println();
        System.out.println(t.toMaster());
    }
}