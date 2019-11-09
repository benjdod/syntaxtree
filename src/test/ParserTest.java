package test;

import java.util.ArrayList;

import base.parse.*;
import base.syntree.Head;
import base.token.Tokenizer;

public class ParserTest {
    public static void main(String[] args) {
        ArrayList<String> strs = Tokenizer.tokens("8/2^2+3"); // = 5
        Head h = Parser.parse(strs);
        System.out.println(h);
        System.out.println(h.calculate());
    }
}