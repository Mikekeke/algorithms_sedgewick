package com.mikekekeke.week_1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int opened;
    private final int gridSide;
    private final boolean[] testGrid;
    private final WeightedQuickUnionUF percUnion;
    private final WeightedQuickUnionUF fullUnion;
    private final int vTop;
    private final int vBot;

    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("N <= 0");
        opened = 0;
        gridSide = n;
        int realSize = n * n + 2;
        vTop = 0;
        vBot = realSize - 1;
        testGrid = new boolean[realSize];
        percUnion = new WeightedQuickUnionUF(realSize);
        fullUnion = new WeightedQuickUnionUF(realSize - 1);
        testGrid[0] = true;
        testGrid[testGrid.length - 1] = true;
        for (int i = 1; i < testGrid.length - 1; i++) {
            testGrid[i] = false;
        }
    }

    private int findIdx(int row, int col) {
        return (gridSide * (row - 1)) + col;
    }

    private void unionWithNeighbors(int idx, int row, int col) {
        // union with opened neighbors
        int topNbrIdx = (gridSide * (row - 2)) + col;
        if (row - 1 > 0 && isOpen(row - 1, col)) {
            percUnion.union(topNbrIdx, idx);
            fullUnion.union(topNbrIdx, idx);
        }
        int botNbrIdx = (gridSide * (row)) + col;
        if (row + 1 <= gridSide && isOpen(row + 1, col)) {
            percUnion.union(botNbrIdx, idx);
            fullUnion.union(botNbrIdx, idx);
        }
        int leftNbrIdx = (gridSide * (row - 1)) + col - 1;
        if (col - 1 > 0 && isOpen(row, col - 1)) {
            percUnion.union(leftNbrIdx, idx);
            fullUnion.union(leftNbrIdx, idx);
        }
        int rightNbrIdx = (gridSide * (row - 1)) + col + 1;
        if (col + 1 <= gridSide && isOpen(row, col + 1)) {
            percUnion.union(rightNbrIdx, idx);
            fullUnion.union(rightNbrIdx, idx);
        }
    }

    public void open(int row, int col) {
        if (row > gridSide || col > gridSide || row < 1 || col < 1) throw new IllegalArgumentException();
        if (isOpen(row, col)) return;
        int idx = findIdx(row, col);
        testGrid[idx] = true;
        opened++;
        unionWithNeighbors(idx, row, col);
        if (row == 1) {
            percUnion.union(idx, vTop);
            fullUnion.union(idx, vTop);
        }
        if (row == gridSide) {
            percUnion.union(idx, vBot);
        }
    }

    public boolean isOpen(int row, int col) {
        if (row > gridSide || col > gridSide || row < 1 || col < 1)
            throw new IllegalArgumentException();
        return testGrid[findIdx(row, col)];
    }

    public boolean isFull(int row, int col) {
        if (row > gridSide || col > gridSide || row < 1 || col < 1)
            throw new IllegalArgumentException();
        int idx = (gridSide * (row - 1)) + col;
        return fullUnion.connected(vTop, idx);
    }

    public int numberOfOpenSites() {
        return opened;
    }

    public boolean percolates() {
            return percUnion.connected(vTop, vBot);
    }

    public static void main(String[] args) {
        Percolation percolation = new Percolation(3);
        percolation.open(1, 3);
        percolation.open(2, 3);
        percolation.open(3, 3);
        percolation.open(3, 1);
        StdOut.println(percolation.isOpen(3, 1));
        StdOut.println(percolation.isFull(3, 1));
        StdOut.println(percolation.isOpen(1, 2));
        StdOut.println(percolation.isFull(1, 2));
        StdOut.println(percolation.isOpen(3, 3));
        StdOut.println(percolation.isFull(3, 3));
    }
}
