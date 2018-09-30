package com.mikekekeke.week2;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.EmptyStackException;

public class WeekTwoRunner {
    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            int rnd = StdRandom.uniform(0, 2);
            if (rnd == 2) throw new IllegalStateException();
            StdOut.println(rnd);
        }
    }
}
