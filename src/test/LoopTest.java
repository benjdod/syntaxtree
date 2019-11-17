package test;

import java.util.ArrayList;
import java.util.Iterator;

public class LoopTest {
    public static void main(String[] args) {
        ArrayList<Integer> intlist = new ArrayList<Integer>();
        intlist.add(10);
        intlist.add(6);
        intlist.add(30);
        intlist.add(7);
        Iterator<Integer> i = intlist.iterator();
        while (i.hasNext()) {
            if (i.next() == 6) {
                continue;
            }
        }
    }
}