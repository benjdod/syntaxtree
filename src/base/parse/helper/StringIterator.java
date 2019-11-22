package base.parse.helper;

public class StringIterator {

    // a character iterator with expanded capabilities to make lexical 
    // analysis easier

    private String _s; 
    private int idx = -1;

    public StringIterator(String s) {
        if (s == null) { throw new IllegalArgumentException("string cannot be null");}
        _s = s;
    }

    public boolean hasNext() {
        if (idx < _s.length()-1) {
            return true;
        } else {
            return false;
        }
    }

    public char next() {
        idx += 1;
        return _s.charAt(idx);
    }

    public char previous() {
        idx -= 1;
        return _s.charAt(idx);
    }

    // handle these exceptions better, go to NoSuchElementException
    public char peekNext() throws IndexOutOfBoundsException {
        return _s.charAt(idx+1);
    }

    public char peekPrev() throws IndexOutOfBoundsException {
        return _s.charAt(idx-1);
    }

    public char first() {
        return _s.charAt(0);
    }

    public char last() {
        return _s.charAt(_s.length()-1);
    }

    public int getIndex() {
        return idx;
    }

}