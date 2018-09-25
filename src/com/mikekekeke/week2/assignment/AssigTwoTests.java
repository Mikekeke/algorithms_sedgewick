package com.mikekekeke.week2.assignment;

import edu.princeton.cs.algs4.StdOut;

public class AssigTwoTests {
    public static void main(String[] args) {
        Deque<String> d = new Deque<>();
        StdOut.println(d.isEmpty());
        d.addFirst("1");
        StdOut.println(d.peekFst());
        StdOut.println(d.peekLst());
        StdOut.println(d.show());
        d.addFirst("2");
        StdOut.println(d.peekFst());
        StdOut.println(d.peekLst());
        StdOut.println(d.show());
        d.addFirst("3");
        StdOut.println(d.peekFst());
        StdOut.println(d.peekLst());
        StdOut.println(d.show());
        d.addFirst("4");
        StdOut.println(d.peekFst());
        StdOut.println(d.peekLst());
        StdOut.println(d.show());
        d.addFirst("5");
        StdOut.println(d.peekFst());
        StdOut.println(d.peekLst());
        StdOut.println(d.show());
        d.addFirst("6");
        StdOut.println(d.peekFst());
        StdOut.println(d.peekLst());
        StdOut.println(d.show());
    }
}
