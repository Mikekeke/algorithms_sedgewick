package com.mikekekeke.week_3.assignment;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class FastCollinearPoints {
    private Point[] points;
    private int segmentsNum = 0;

    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();
        this.points = points;
        Arrays.sort(this.points);
        if (this.points[0] == null) throw new IllegalArgumentException();
        for (int i = 0; i < this.points.length - 1; i++) {
            Point p1 = this.points[i];
            Point p2 = this.points[i + 1];
            if (p2 == null) throw new IllegalArgumentException();
            if (p1.compareTo(p2) == 0) throw new IllegalArgumentException();
        }
    }

    public int numberOfSegments() {

        return 0;
    }

    public LineSegment[] segments() {
//        for (int i = 0; i < points.length; i++) {
//
//        }
        Point p = points[0];
        Arrays.sort(points, p.slopeOrder());
        StdOut.println(Arrays.toString(points));

        return new LineSegment[0];
    }
}
