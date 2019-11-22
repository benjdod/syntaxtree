package test;

import java.util.ArrayList;

import base.parse.*;
import base.tree.struct.Head;
import base.parse.Lexer;

public class ParserTest {
    public static void main(String[] args) {
        ArrayList<String> strs = Lexer.tokens("(9+(2-1))*3"); 
        Head h = Parser.parse(strs);
        System.out.println(h);
        System.out.println(h.calculate());
    }
}