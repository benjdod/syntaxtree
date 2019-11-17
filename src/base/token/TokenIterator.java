package base.token;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class TokenIterator implements Iterator<String> {
    private ArrayList<String> _s;
    private int _idx;
    public TokenIterator(ArrayList<String> s) {
        if (s == null) {throw new IllegalArgumentException("provided array cannot be null");}
        _s = s;
        _idx = -1;
    }

    public boolean hasTwoNext() {
        if ((_idx+2) >= _s.size()) {
            return false;
        } 
        return true;
    }

    public boolean hasNext() {
        if ((_idx+1) >= _s.size()) {
            return false;
        } 
        return true;
    }

    public String next() {
        if (hasNext()) {
            _idx++;
            return _s.get(_idx);
        } else {
            throw new NoSuchElementException("the iterator is out of elements");
        }
        
    }

    public String current() {
        return _s.get(_idx);
    }
}