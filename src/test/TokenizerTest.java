package test;

import java.util.ArrayList;

import base.token.Tokenizer;;

public class TokenizerTest {
    public static void main(String[] args) {
        String s = "3(8-7)*8+4";
        ArrayList<String> t = Tokenizer.tokens(s);
        for (String token : t) {
            System.out.print(token + "  ");
        }
    }
}