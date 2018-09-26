package com.mikekekeke.week2.assignment;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private Item[] inner;
    private int front;
    private int back;
    private int first;
    private int last;
    private int count;

    public Deque() {
        front = 1;
        back = 1;
        first = 0;
        last = 1;
        inner = (Item[]) new Object[2];
        count = 0;

    }

    public boolean isEmpty() {
        return last - first == 1;
    }

    public int size() {
        return count;
    }

    private void resizeFront(int newFront) {
        int currSize = inner.length;
        front = newFront;
        Item[] resized = (Item[]) new Object[newFront + currSize];
        for (int i = 0; i < last; i++) {
            resized[newFront + i] = inner[i];
        }
        first += newFront;
        last += newFront;
        inner = resized;
    }

    private void shrinkFront(int newFront) {
        try {
            int currSize = inner.length;
            front = newFront;
            Item[] resized = (Item[]) new Object[currSize - first + newFront];
            for (int i = first, k = 0; i < last; i++, k++) {
                Item f = inner[i];
                resized[k + newFront - 1] = f;
            }
            first -= newFront;
            last -= newFront;
            inner = resized;
        } catch (Exception e) {
            StdOut.println(" ERRR / "
                    + inner.length +
                    " / cnt: " + count +
                    " / fr: " + front +
                    " / ba: " + back +
                    " / fst: " + first +
                    " / lst: " + last);
            throw e;
        }

    }

    private void resizeBack(int newBack) {
        int currSize = inner.length;
        Item[] resized = (Item[]) new Object[currSize + newBack];
//        Item[] resized = (Item[]) new Object[currSize * 2];
        for (int i = first; i < last; i++) {
            resized[i] = inner[i];
        }
        back = newBack;
        inner = resized;
    }

    public void addFirst(Item item) {
        check(first >= -1);
        if (first == -1) resizeFront(front * 2);
            inner[first] = item;
            first--;
        count++;
    }

    public void addLast(Item item) {
        if (last == inner.length) resizeBack(back * 2);
        count++;
        inner[last] = item;
        last++;
    }

    public Item removeFirst() {
        count--;
        first++;
        inner[first] = null;
        if (first >= count * 3) shrinkFront(first / 2);

        return null;
    }

    public Item removeLast() {
        count--;
        return null;
    }

    public Iterator<Item> iterator() {
        return null;
    }

    public static void main(String[] args) {

    }

    public Item peekFst() {
        return inner[first + 1];
    }

    public Item peekLst() {
        return inner[last - 1];
    }

    public String show() {
        return Arrays.toString(inner) + " / "
                + inner.length +
                " / cnt: " + count +
                " / fr: " + front +
                " / ba: " + back +
                " / fst: " + first +
                " / lst: " + last;
    }

    private void check(boolean condition) {
        if (!condition) throw new IllegalStateException("Bad");
    }
}
