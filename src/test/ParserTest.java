package test;

import java.util.ArrayList;

import base.parse.*;
import base.tree.struct.Head;
import base.parse.Lexer;

public class ParserTest {

    // Demonstrates a full parsing session from input text to evaluatable tree

    public static void main(String[] args) {
        ArrayList<String> strs = Lexer.tokens("(9+(2-1))*3"); 
        Head h = Parser.parse(strs);
        System.out.println(h);
        System.out.println(h.calculate());
    }
}