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
        StdOut.println("Shrink front");
        try {
            int currSize = inner.length;
            front = newFront;
            Item[] resized = (Item[]) new Object[currSize - first + newFront];
            for (int i = first+1, k = 0; i < last; i++, k++) {
                Item f = inner[i];
                resized[k + newFront] = f;
            }
            first = newFront-1;
            last = newFront + count;
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
    private void shrinkBack(int newBack) {
        int currSize = inner.length;
        Item[] resized = (Item[]) new Object[currSize - (currSize - last) + newBack];
        for (int i = first+1; i < last; i++) {
            Item f = inner[i];
            resized[i] = f;
        }
        back = newBack;
        inner = resized;
    }


    private void resizeBack(int newBack) {
        int currSize = inner.length;
        Item[] resized = (Item[]) new Object[currSize + newBack];
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
        if(isEmpty()) throw new IllegalStateException("Empty deque");
        count--;
        first++;
        Item it = inner[first];
        inner[first] = null;
        if (first >= count * 3) shrinkFront(first / 2);
        int emptyBack = inner.length - last;
        if (emptyBack >= count * 3) shrinkBack(emptyBack / 2);

        return it;
    }

    public Item removeLast() {
        count--;
        last--;
        Item it = inner[last];
        inner[last] = null;
        int emptyBack = inner.length - last;
        if (emptyBack >= count * 3) shrinkBack(emptyBack / 2);
        if (first >= count * 3) shrinkFront(first / 2);
        return it;
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
                " / fst: " + first + "(" + peekFst() +")" +
                " / lst: " + last + "(" + peekLst() +")";
    }

    private void check(boolean condition) {
        if (!condition) throw new IllegalStateException("Bad");
    }
}
