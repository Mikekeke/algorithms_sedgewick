package com.mikekekeke.week_3.assignment;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.LinkedList;

public class BruteCollinearPoints {
    private Point[] points;
    private int segmentsNum = 0;

    public BruteCollinearPoints(Point[] points) {
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
        return segmentsNum;
    }

    private boolean isSameSegments(Point[] a, Point[] b) {
        boolean res1 = a[0].compareTo(b[0]) == 0 && a[1].compareTo(b[1]) == 0;
        boolean res2 = a[1].compareTo(b[0]) == 0 && a[1].compareTo(b[0]) == 0;
        return res1 || res2;
    }

    public LineSegment[] segments() {
        LinkedList<Point[]> lss = new LinkedList<>();
        for (int i = 0; i < points.length - 3; i++) {
            for (int j = i + 1; j < points.length - 2; j++) {
                double s1 = points[i].slopeTo(points[j]);
                for (int k = j + 1; k < points.length - 1; k++) {
                    double s2 = points[j].slopeTo(points[k]);
                    if (s1 != s2) continue;
                    for (int l = k + 1; l < points.length; l++) {
                        double s3 = points[k].slopeTo(points[l]);
                        if (s1 == s3) {
                            Point[] arr = {points[i], points[l]};
                            if (lss.isEmpty()) {
                                lss.add(arr);
                                segmentsNum++;
                            } else {
                                if (!isSameSegments(arr, lss.peekLast())) {
                                    lss.add(arr);
                                    segmentsNum++;
                                }
                            }
                        }
                    }
                }
            }
        }

        LineSegment[] result = new LineSegment[segmentsNum];
        for (int i = 0; i < segmentsNum; i++) {
            Point[] arr = lss.pop();
            result[i] = new LineSegment(arr[0], arr[1]);
        }
        return result;
    }

    public static void main(String[] args) {
        Point[] ps = new Point[5];
        ps[0] = new Point(2, 3);
        ps[1] = new Point(2, 4);
        ps[2] = new Point(2, 9);
        ps[3] = new Point(2, 20);
        ps[4] = new Point(2, 40);
        BruteCollinearPoints bcp = new BruteCollinearPoints(ps);
        StdOut.println(Arrays.toString(bcp.segments()));

    }
}
