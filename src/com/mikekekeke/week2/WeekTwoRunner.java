package com.mikekekeke.week2;

import edu.princeton.cs.algs4.StdOut;

import java.util.EmptyStackException;

public class WeekTwoRunner {
    public static void main(String[] args) {
        StdOut.println("StringStack queue");
        StringStack ss = new StringStack();
        try {
            ss.pop();
        } catch (IllegalStateException ex) {
            StdOut.println("OK");
        }
        try {
            ss.peek();
        } catch (IllegalStateException ex) {
            StdOut.println("OK");
        }
        try {
            ss.peek();
        } catch (IllegalStateException ex) {
            StdOut.println("OK");
        }
        ss.push("1");
        ss.push("2");
        ss.push("3");
        StdOut.println(ss.peek());
        StdOut.println(ss.pop());
        StdOut.println(ss);
        StdOut.println(ss.pop());
        StdOut.println(ss);
        StdOut.println(ss.pop());
        StdOut.println(ss);
        try {
            ss.peek();
        } catch (IllegalStateException ex) {
            StdOut.println("OK");
        }

        StdOut.println("Stack queue");
        StackQueue<String> sqs = new StackQueue<>();
        sqs.enqueue("1");
        sqs.enqueue("2");
        StdOut.println(sqs.dequeue());
        sqs.enqueue("3");
        sqs.enqueue("4");
        StdOut.println(sqs.dequeue());
        StdOut.println(sqs.dequeue());
        StdOut.println(sqs.dequeue());
        try {
            StdOut.println(sqs.dequeue());
        } catch (EmptyStackException ignored){}

        StdOut.println("StackMax");
        StackMax sm = new StackMax();
        sm.push(1);
        StdOut.println(sm.max() == 1);
        sm.push(2);
        StdOut.println(sm.max() == 2);
        sm.push(9);
        StdOut.println(sm.max() == 9);
        sm.push(3);
        StdOut.println(sm.max() == 9);
        sm.pop();
        sm.pop();
        StdOut.println(sm.max() == 2);
    }
}
