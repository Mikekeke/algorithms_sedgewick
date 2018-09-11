package com.mikekekeke.week_1;

public class WeekOneRunner {

    public static void main(String[] args) {
        QuickFindUF UF = new QuickFindUF(10);
        UF.union(0,1);
        UF.union(2,3);
        UF.union(1,2);
        System.out.println("UF should true: " + UF.connected(1,3));
        QuickUF UF2 = new QuickUF(10);
        UF2.union(0,1);
        UF2.union(9,1);
        UF2.union(2,3);
        UF2.union(6,3);
        UF2.union(6,7);
        UF2.union(1,2);
        System.out.println("UF2 should true: " + UF2.connected(9,7));
        System.out.println("UF2 should false: " + UF2.connected(9,4));

    }
}
