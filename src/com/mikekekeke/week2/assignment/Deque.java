package com.mikekekeke.week2.assignment;

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

    private void resizeFront() {
        int currSize = inner.length;
        first = currSize+first;
        Item[] resized = (Item[]) new Object[currSize * 2];
        for (int i = 0; i < last; i++) {
            resized[currSize + i] = inner[i];
        }
        last += currSize;
        inner = resized;
    }

    private void resizeBack() {
        int currSize = inner.length;
        back = back * 2;
        Item[] resized = (Item[]) new Object[currSize + back];
//        Item[] resized = (Item[]) new Object[currSize * 2];
        for (int i = first; i < last; i++) {
            resized[i] = inner[i];
        }
        inner = resized;
    }


    public void addFirst(Item item) {
        check(first >= -1);
        if (first == -1) resizeFront();
        count++;
        inner[first] = item;
        first--;
    }

    public void addLast(Item item) {
        if (last == inner.length) resizeBack();
        count++;
        inner[last] = item;
        last++;
    }

    public Item removeFirst() {
        count--;

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
        return inner[first+1];
    }

    public Item peekLst() {
        return inner[last-1];
    }

    public String show() {
        return Arrays.toString(inner);
    }

    private void check(boolean condition) {
        if(!condition) throw new IllegalStateException("Bad");
    }
}
