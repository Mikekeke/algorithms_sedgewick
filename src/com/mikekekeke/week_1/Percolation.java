package com.mikekekeke.week_1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int opened;
    private final int gridSide;
    private final boolean[] testGrid;
    private final WeightedQuickUnionUF wu;
    private final int layers;

    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("N <= 0");
        layers = n;
        opened = 0;
        gridSide = n;
        int realSize = n * n + 2;
        testGrid = new boolean[realSize];
        wu = new WeightedQuickUnionUF(realSize);
        testGrid[0] = true;
        testGrid[testGrid.length - 1] = true;
        for (int i = 1; i < testGrid.length - 1; i++) {
            testGrid[i] = false;
        }

        // create virtual top and bot
        int cnt = n;
        while (cnt != 0) {
            wu.union(0, cnt);
            wu.union(realSize - 1, realSize - 1 - cnt);
            cnt--;
        }
    }

    private int findIdx(int row, int col) {
        return (gridSide * (row - 1)) + col;
    }

    private void unionWithNeighbors(int idx, int row, int col) {
        // union with opened neighbors
        int topNbrIdx = (gridSide * (row - 2)) + col;
        if (row - 1 > 0 && isOpen(row - 1, col)) wu.union(topNbrIdx, idx);
        int botNbrIdx = (gridSide * (row)) + col;
        if (row + 1 <= gridSide && isOpen(row + 1, col)) wu.union(botNbrIdx, idx);
        int leftNbrIdx = (gridSide * (row - 1)) + col - 1;
        if (col - 1 > 0 && isOpen(row, col - 1)) wu.union(leftNbrIdx, idx);
        int rightNbrIdx = (gridSide * (row - 1)) + col + 1;
        if (col + 1 <= gridSide && isOpen(row, col + 1)) wu.union(rightNbrIdx, idx);
    }

    public void open(int row, int col) {
        if (row > gridSide || col > gridSide || row < 1 || col < 1) throw new IllegalArgumentException();
        if (isOpen(row, col)) return;
        int idx = findIdx(row,col);
        testGrid[idx] = true;
        opened++;
        unionWithNeighbors(idx, row, col);
    }

    public boolean isOpen(int row, int col) {
        if (row > gridSide || col > gridSide || row < 1 || col < 1)
            throw new IllegalArgumentException();
        return testGrid[findIdx(row, col)];
    }

    public boolean isFull(int row, int col) {
        if (row > gridSide || col > gridSide) throw new IllegalArgumentException();
        int idx = (gridSide * (row - 1)) + col;
        return isOpen(row, col) && wu.connected(0, idx);
    }

    public int numberOfOpenSites() {
        return opened;
    }

    public boolean percolates() {
        if (layers == 1) {
            return opened > 0;
        } else {
            return wu.connected(0, testGrid.length - 1);
        }
    }

    public static void main(String[] args) {
        Percolation percolation = new Percolation(3);
        percolation.open(1, 3);
        percolation.open(2, 3);
        percolation.open(3, 3);
        percolation.open(3, 1);
        StdOut.println(percolation.isOpen(3, 1));
        StdOut.println(percolation.isFull(3, 1));
    }
}
