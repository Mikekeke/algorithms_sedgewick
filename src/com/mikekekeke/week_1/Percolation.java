package com.mikekekeke.week_1;

import edu.princeton.cs.algs4.QuickFindUF;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int opened;
    private int gridSide;
    private boolean[] testGrid;
    public Percolation(int n){
        if (n <= 0) throw new IllegalArgumentException("N <= 0");
        opened = 0;
        gridSide = n;
        testGrid = new boolean[n*n];
        for (int i = 0; i < testGrid.length; i++) {
            testGrid[i] = false;
        }
    }                // create n-by-n grid, with all sites blocked

    public void open(int row, int col) {
        int idx = gridSide * (row - 1) + (col - 1);
        testGrid[idx] = true;
        opened ++;
    } // open site (row, col) if it is not open already

    public boolean isOpen(int row, int col) {
        int idx = gridSide * (row - 1) + (col - 1);
        return testGrid[idx];
    }

    public boolean isFull(int row, int col) {
        return !isOpen(row, col);
    }

    public int numberOfOpenSites() {
        return opened;
    }

    public boolean percolates()              // does the system percolate?
    {
        return false;
    }

    public static void main(String[] args) {
        Percolation P = new Percolation(2);
        StdOut.println(P.isOpen(1,1));
        P.open(1,1);
        StdOut.println(P.isOpen(1,1));
        StdOut.println(P.isOpen(1,2));
        StdOut.println(P.isOpen(2,2));
        P.open(2,2);
        StdOut.println(P.isOpen(2,2));

    }   // test client (optional)
}
