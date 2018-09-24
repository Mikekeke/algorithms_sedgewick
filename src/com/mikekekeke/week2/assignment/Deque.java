package com.mikekekeke.week2.assignment;

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private Item[] inner;
    private int frontPadding;
    private int backPadding;
    private int first;
    private int last;
    private int count;
    public Deque() {
        frontPadding = 1;
        backPadding = 1;
        first = 1;
        last = 1;
        inner = (Item[]) new Object[frontPadding + 1 + backPadding];
        count = 0;

    }

    public boolean isEmpty() {
        return first == last;
    }

    public int size() {
        return count;
    }

    public void addFirst(Item item) {
        count++;

    }

    public void addLast(Item item) {
        count++;

    }

    public Item removeFirst() {
        return null;
    }

    public Item removeLast() {
        return null;
    }

    public Iterator<Item> iterator() {
        return null;
    }

    public static void main(String[] args) {

    }
}
