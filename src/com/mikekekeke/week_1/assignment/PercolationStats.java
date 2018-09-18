package com.mikekekeke.week_1.assignment;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double CONFIDENCE_Q = 1.96;
    private final int[] results;
    private final int totalSites;
    private final int totalTrials;
    private double mean = Double.NaN;
    private double stddev = Double.NaN;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException();
        totalSites = n * n;
        totalTrials = trials;
        results = new int[trials];
        for (int i = 0; i < trials; i++) {
            Percolation p = new Percolation(n);
            boolean percolates = false;
            while (!percolates) {
                p.open(StdRandom.uniform(n) + 1, StdRandom.uniform(n) + 1);
                percolates = p.percolates();
            }
            results[i] = p.numberOfOpenSites();
        }
    }

    public double mean() {
        if (Double.isNaN(mean)) mean = StdStats.mean(results) / totalSites;
        return mean;
    }

    public double stddev() {
        if (Double.isNaN(stddev)) stddev = StdStats.stddev(results) / totalSites;
        return stddev;
    }

    public double confidenceLo() {
        return mean() - (CONFIDENCE_Q * stddev() / Math.sqrt(totalTrials));
    }

    public double confidenceHi() {
        return mean() + (CONFIDENCE_Q * stddev() / Math.sqrt(totalTrials));
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(n, trials);
        StdOut.println("mean                    = " + ps.mean());
        StdOut.println("stddev                  = " + ps.stddev());
        StdOut.println("95% confidence interval = "
                + "[" + ps.confidenceLo() + ", " + ps.confidenceHi() + "]");

    }
}
