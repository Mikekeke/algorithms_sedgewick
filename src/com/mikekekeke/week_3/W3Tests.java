package com.mikekekeke.week_3;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Comparator;

class Num {
    int value;
    Num(int x) {
        value = x;
    }

    @Override
    public String toString() {
        return "N(" + value + ")";
    }

    public Comparator<Num> getComp() {

        return new Comparator<Num>() {
            @Override
            public int compare(Num o1, Num o2) {
                return (Num.this.value - o1.value) - (Num.this.value - o2.value);
            }
        };
    }
}

public class W3Tests {
    public static void main(String[] args) {
        Num[] nums = {
                new Num(8),
                new Num(1),
                new Num(3),
                new Num(9),
                new Num(2)
        };
        Num cn = new Num(-100);
        Arrays.sort(nums, cn.getComp());
        StdOut.println(Arrays.toString(nums));
    }
}
