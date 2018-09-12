package com.mikekekeke.week_1;

import java.util.LinkedList;
import java.util.List;

public class WeekOneRunner {

    public static void main(String[] args) {
        List<QU> UNS = new LinkedList<>();
        UNS.add(new QuickFindUF(10));
        UNS.add(new QuickUnionUF(10));
        UNS.add(new QuickUnionUFW(10));
        testAll(UNS);
    }

    public static void testAll(List<QU> unions) {
        for (QU union : unions) {
            String name = union.getClass().getSimpleName();
            union.union(0,1);
            union.union(9,1);
            union.union(8,9);
            union.union(6,3);
            union.union(6,7);
            union.union(3,2);
            union.union(1,2);
            System.out.println(name + " should true: " + union.connected(8,1));
            System.out.println(name + " should true: " + union.connected(8,7));
            System.out.println(name + " should false: " + union.connected(9,4));
        }
    }
}
