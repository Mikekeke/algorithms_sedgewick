package com.mikekekeke.week2.assignment;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int n;
    private Item[] data;
    private int fst;
    private int lst;

    public RandomizedQueue() {
        data = (Item[]) new Object[1];
        n = 0;
        fst = 0;
        lst = 0;
    }

    private int getRandomIndex() {
        return StdRandom.uniform(fst, lst);
    }

    public boolean isEmpty() {
        if (n == 0 && (lst != 0 || fst != 0)) throw new IllegalStateException("Bad empty");
        return n == 0;
    }

    public int size() {
        if (n == 0 && (lst != 0 || fst != 0)) throw new IllegalStateException("Bad size");
        return n;
    }

    private void resizeArray() {
        Item[] resized = (Item[]) new Object[n * 2];
        for (int i = fst, k = 0; i < lst; i++, k++) {
            Item it = data[i];
            if (it == null) {
                k--;
                continue;
            }
            resized[k] = it;
        }
        fst = 0;
        lst = n;
        data = resized;
    }

    public void enqueue(Item item) {
        if (lst == data.length) resizeArray();
        data[lst++] = item;
        n++;
    }


    public Item dequeue() {
        if (n < 1) throw new NoSuchElementException("Empty queue");
        if (data.length / 2 >= n) resizeArray();
        Item it = null;
        while (it == null) {
            int idx = getRandomIndex();
            it = data[idx];
            data[idx] = null;
        }
        n--;
        return it;
    }

    public Item sample() {
        Item it = null;
        while (it == null) {
            it = data[getRandomIndex()];
        }
        return it;
    }

    public Iterator<Item> iterator() {
        return null;
    }

    public String show() {
        return Arrays.toString(data);
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> rd = new RandomizedQueue<>();
        StdOut.println("enqueue");
        for (int i = 0; i < 2; i++) {
            rd.enqueue(i);
            StdOut.println(rd.show());

        }
        rd.dequeue();
        StdOut.println("\nsample");
        for (int i = 0; i < 7; i++) {
            StdOut.println(rd.sample());
        }
        StdOut.println("\ndeque");
        for (int i = 0; i < 2; i++) {
            StdOut.println(rd.dequeue());
            StdOut.println(rd.show());
        }
//

    }

}