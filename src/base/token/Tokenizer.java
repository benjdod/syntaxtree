package base.token;

import java.util.ArrayList;

public class Tokenizer {
    public static ArrayList<String> tokens(String str) {

        if (str == null) { throw new IllegalArgumentException("input string cannot be null"); }

        // ----- VARIABLE INITIALIZATION -----

        // start simple
        ArrayList<String> tokens = new ArrayList<String>();
        StringBuffer s = new StringBuffer();
        char c;

        String t = str.replaceAll("\\s","");

        StringIterator i = new StringIterator(t);

        // ----- DEFENSIVE INPUT CHECKS -----

        // even number of parentheses
        int lparen = 0;
        int rparen = 0;
        for (int j = 0; j < t.length(); j++) {
            if (t.charAt(j) == '(') {
                lparen++;
            }
            if (t.charAt(j) == ')') {
                rparen++;
            }
        }
        if (lparen != rparen) {throw new IllegalArgumentException("number of opening and closing parentheses must be equal"); }
        // out of place character checks
        if (i.first() == ')') {throw new IllegalArgumentException("equation begins with a closing parenthesis"); }
        if (isOp(i.first()) && i.first() != '-') {throw new IllegalArgumentException("equation begins with an operator"); }
        if (isOp(i.last())) {throw new IllegalArgumentException("equation ends with an operator"); }
        // proper subset check (the equation only contains legal characters)
        char[] legalchars = {
            '0','1','2','3','4','5','6','7','8','9','+','-','*','/','^','(',')','.'
        };
        if (!isSubset(t, legalchars)) { throw new IllegalArgumentException("input string contains illegal chars.\nLegal chars are digits 0-9, '+', '-', '*', '/', '^', '.', '(', and ')'"); }
        // conjunctive operator and parentheses checks
        // each number can only be followed by an operator or a parenthesis
        // operators must be bookended by numbers 

        // ----- TOKENIZER BODY -----

        // handles if the first char is a negative sign
        if (i.first() == '-') {
            // appends '-' to buffer and advances 
            // iterator to the first value
            s.append(i.next());
        }

        // main loop
        while (i.hasNext()) {
            c = i.next();
            if (!isDigit(c)) {
                switch (c) {
                    case '(':
                        if (isOp(i.peekNext()) && i.peekNext() != '-') { throw new IllegalArgumentException("'(' was followed by an operator");}
                        tokens.add(Character.toString(c));
                        break;
                    case ')':
                        if (isOp(i.peekPrev())) { throw new IllegalArgumentException("')' was preceded by an operator");}
                        tokens.add(Character.toString(c));
                        break;
                    case '+':
                        if (isOp(i.peekPrev())) { throw new IllegalArgumentException("'+' operator is not preceded by an operand, was preceded by " + i.peekPrev());}
                        if (isOp(i.peekNext())) { throw new IllegalArgumentException("'+' operator is not followed by an operand, was followed by " + i.peekNext());}
                        tokens.add(Character.toString(c));
                        break;
                    case '-':
                        // try block for peek method, reduntant because defenses 
                        // check to make sure 
                        try {
                            if (isDigit(i.peekNext())) {
                                // should have a try block too, but defenses 
                                // check for starting character beginning with a '-',
                                // so indexing shouldn't be a problem...
                                if (!isDigit(i.peekPrev()) && i.peekPrev() != ')') {
                                    s.append(c);
                                } else {
                                    tokens.add(Character.toString(c));
                                }
                            }
                        } catch (IndexOutOfBoundsException e) {
                            throw new IllegalArgumentException("input string ended with a negative sign ('-')");
                        }
                        break;
                    case '*':
                        if (isOp(i.peekPrev())) { throw new IllegalArgumentException("'*' operator is not preceded by an operand, was preceded by " + i.peekPrev());}
                        if (isOp(i.peekNext())) { throw new IllegalArgumentException("'*' operator is not followed by an operand, was followed by " + i.peekNext());}
                        tokens.add(Character.toString(c));
                        break;
                    case '/':
                        if (isOp(i.peekPrev())) { throw new IllegalArgumentException("'/' operator is not preceded by an operand, was preceded by " + i.peekPrev());}
                        if (isOp(i.peekNext())) { throw new IllegalArgumentException("'/' operator is not followed by an operand, was followed by " + i.peekNext());}   
                        tokens.add(Character.toString(c));
                        break;
                    case '^':
                        if (isOp(i.peekPrev())) { throw new IllegalArgumentException("'^' operator is not preceded by an operand, was preceded by " + i.peekPrev());}
                        if (isOp(i.peekNext())) { throw new IllegalArgumentException("'^' operator is not followed by an operand, was followed by " + i.peekNext());}   
                        tokens.add(Character.toString(c));
                        break;
                    default:
                        System.out.println("something is wrong, the system should not have reached this point! (the current character is likely not a legal digit, operator, or parenthesis)");
                }
            } else {
                // is digit stuff
                try {
                    if (isDigit(i.peekNext())) {
                        s.append(c);
                    } else {
                        s.append(c);
                        tokens.add(s.toString());
                        s.setLength(0);
                    }
                } catch (IndexOutOfBoundsException e) {
                    s.append(c);
                    tokens.add(s.toString());
                    s.setLength(0);
                }
                
            }
        }
        return tokens;
    }

    private static boolean isDigit(char c) {
        if ((int) c <= 57 && (int) c >= 48) {
            return true;
        } else if (c == '.') {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isOp(char c) {
        if (c == '+' || c == '-' || c == '*' || c == '/') {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isOpOrParen(char c) {
        if (isOp(c) || c == '(' || c == ')') {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isSubset(String str, char[] charlist) {
        boolean total = true;
        boolean isin;
        for (int i = 0; i < str.length(); i++) {
            isin = false;
            for (char c : charlist) {
                if (c == str.charAt(i)) {
                    isin = true;
                } else {
                    isin = isin || false;
                }
            }
            total = total && isin;
        }
        return total;
    }
}