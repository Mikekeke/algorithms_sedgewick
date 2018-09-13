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

        QuickUnionFind QUF = new QuickUnionFind(10);
        QUF.union(0, 1);
        QUF.union(0, 2);
        QUF.union(6, 9);
        assertEq(QUF.find(0), 2);
        QUF.union(4, 6);
        assertEq(QUF.find(0), 2);
        QUF.union(0, 4);
        assertEq(QUF.find(0), 9);

        SuccDelete SD = new SuccDelete(10);
        assertEq(SD.successor(4), 5);
        SD.delete(4);
        SD.delete(3);
        SD.delete(6);
        assertEq(SD.successor(2), 5);
        SD.delete(5);
        assertEq(SD.successor(2), 7);
        assertEq(SD.successor(9), 9);
    }

    public static void assertEq(int a, int b) {
        if (a != b) {
            throw new IllegalStateException("!Not equal: " + a + "!=" + b);
        }
    }

    public static void testAll(List<QU> unions) {
        for (QU union : unions) {
            String name = union.getClass().getSimpleName();
            union.union(0, 1);
            union.union(9, 1);
            union.union(8, 9);
            union.union(6, 3);
            union.union(6, 7);
            union.union(3, 2);
            union.union(1, 2);
            if (!union.connected(8, 1)) {
                throw new IllegalStateException(name + ": should be connected: " + 8 + " " + 1);
            }
            if (!union.connected(8, 7)) {
                throw new IllegalStateException(name + ": should be connected: " + 8 + " " + 7);
            }
            if (union.connected(9, 4)) {
                throw new IllegalStateException(name + ": should NOT be connected: " + 9 + " " + 4);
            }
        }
    }
}
