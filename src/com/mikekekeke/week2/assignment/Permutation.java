package com.mikekekeke.week2.assignment;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class Permutation {

    public static void main(String[] args) {

        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> rqs = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            rqs.enqueue(s);
        }

        Iterator<String> is = null;
        for (int i = 0; i < k; i++) {
            if (is == null || !is.hasNext()) {
                is = rqs.iterator();
                StdOut.println(is.next());
            } else StdOut.println(is.next());
        }
    }
}
