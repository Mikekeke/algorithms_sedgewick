package com.mikekekeke.week2.assignment;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Test 8: Insert T items into queue; then iterate over queue and check
 *         that worst-case constant memory is allocated or deallocated
 *         per iterator operation.
 *   * T = 64
 *   * T = 128
 *     - failed on trial 96 of 128
 *     - when the randomized queue contains 128 objects,
 *     - with 33 objects remaining to be iterated over;
 *     - the call to next() caused a change in memory of -512 bytes
 *     - any change of more than 480 bytes fails the test
 *
 *   * T = 256
 *     - failed on trial 192 of 256
 *     - when the randomized queue contains 256 objects,
 *     - with 65 objects remaining to be iterated over;
 *     - the call to next() caused a change in memory of -1024 bytes
 *     - any change of more than 480 bytes fails the test
 *
 * ==> FAILED
 * Seems like memory change caused by array shrinking.
 * Maybe do another implementation of iterator,
 * where possible to iterate over preallocated randomized array
 */

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

    public static void main(String[] args) {

    }

    private int getRandomIndex() {
        return StdRandom.uniform(fst, lst);
    }

    private RandomizedQueue<Item> cloneQueue() {
        RandomizedQueue<Item> cloned = new RandomizedQueue<>();
        Item[] clonedData = (Item[]) new Object[data.length];
        for (int i = 0; i < data.length; i++) {
            clonedData[i] = data[i];
        }
        cloned.data = clonedData;
        cloned.n = n;
        cloned.fst = fst;
        cloned.lst = lst;
        return cloned;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    private void resizeArray() {
        int newSize = n == 0 ? 1 : n * 2;
        Item[] resized = (Item[]) new Object[newSize];
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
        if (item == null) throw new IllegalArgumentException();
        if (lst == data.length) resizeArray();
        data[lst++] = item;
        n++;
    }

    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Empty queue");
        int idx = getRandomIndex();
        Item it;
        if (idx == fst) {
            it = data[fst];
        } else {
            it = data[idx];
            data[idx] = data[fst];
        }
        data[fst] = null;
        n--;
        if (!isEmpty()) fst++;
        if (it == null) throw new IllegalStateException("Idx = " + idx + " n = " + n);
        if (data.length / 4 >= n) resizeArray();
        return it;
    }

    public Item sample() {
        if (n == 0) throw new NoSuchElementException();
        return data[getRandomIndex()];
    }

    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private RandomizedQueue<Item> iterQueue = RandomizedQueue.this.cloneQueue();

            @Override
            public boolean hasNext() {
                return !iterQueue.isEmpty();
            }

            @Override
            public Item next() {
                if (iterQueue.isEmpty()) throw new NoSuchElementException();
                return iterQueue.dequeue();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}