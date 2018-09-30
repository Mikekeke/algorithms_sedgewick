package com.mikekekeke.week2;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.EmptyStackException;

public class WeekTwoRunner {
    public static void main(String[] args) {
        int[] a1 = {1,2,3,4};
        int[] a2 = a1;
        a1[2] = -100;
        StdOut.println(a2[2]);
    }
}
