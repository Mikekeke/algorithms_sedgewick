package com.mikekekeke.week2.assignment;

import edu.princeton.cs.algs4.StdOut;

public class AssigTwoTests {
    public static void main(String[] args) {
        Deque<String> d = new Deque<>();
        StdOut.println(d.isEmpty());
        int fadd = 2;
        for (int i = 1; i <= fadd; i++) {
            d.addFirst("" + i);
//            StdOut.println(d.peekFst());
//            StdOut.println(d.peekLst());
            StdOut.println(d.show());
        }

        int badd = 10;
        for (int i = 1; i <= badd; i++) {
            d.addLast("-" + i);
//            StdOut.println(d.peekFst());
//            StdOut.println(d.peekLst());
            StdOut.println(d.show());
        }

        int frem = 10;
        for (int i = 1; i <= frem; i++) {
            d.removeFirst();
//            StdOut.println(d.peekFst());
//            StdOut.println(d.peekLst());
            StdOut.println(d.show());
        }
    }
}
