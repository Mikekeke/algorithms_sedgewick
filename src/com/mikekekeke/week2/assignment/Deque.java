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
        front = 1;
        back = 1;
        first = 1;
        last = 2;
        inner = (Item[]) new Object[front + 2 + back];
        count = 0;

    }

    public boolean isEmpty() {
        return last - first == 1;
    }

    public int size() {
        return count;
    }

    private void resizeFront(int size) {
        Item[] resized = (Item[]) new Object[size + inner.length];
        for (int i = 0; i < inner.length; i++) {
            resized[size + i] = inner[i];
        }
        first = size;
        last = size+last;
        inner = resized;
    }

    public void addFirst(Item item) {
        count++;
        inner[first] = item;
        if (first == 0) resizeFront(front * 2);
        first--;
    }

    public void addLast(Item item) {
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
}
