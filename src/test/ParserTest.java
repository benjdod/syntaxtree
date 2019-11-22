package test;

import java.util.ArrayList;

import base.parse.*;
import base.syntree.Head;
import base.token.Tokenizer;

public class ParserTest {
    public static void main(String[] args) {
        ArrayList<String> strs = Tokenizer.tokens("(9+(2-1))*3"); 
        Head h = Parser.parse(strs);
        System.out.println(h);
        System.out.println(h.calculate());
    }
}