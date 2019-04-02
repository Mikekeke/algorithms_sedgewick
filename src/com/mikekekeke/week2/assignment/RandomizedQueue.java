package com.mikekekeke.week2.assignment;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int items_stored;
    private Item[] data;
    private int fst;
    private int lst;

    public RandomizedQueue() {
        data = (Item[]) new Object[1];
        items_stored = 0;
        fst = 0;
        lst = 0;
    }

    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        for (int i = 0; i < 10; i++) {
            rq.enqueue("test-" + i);
        }
        Iterator<String> rqit1 = rq.iterator();
        for (int i = 0; i < 10; i++) {
            StdOut.println(rqit1.next());
        }
        rq.enqueue("end");
        StdOut.println("***");
        Iterator<String> rqit2 = rq.iterator();
        for (int i = 0; i < 11; i++) {
            StdOut.println(rqit2.next());
        }
        StdOut.println(rqit2.hasNext());
        rqit2.next();

    }

    private int getRandomIndex() {
        return StdRandom.uniform(fst, lst);
    }

    public boolean isEmpty() {
        return items_stored == 0;
    }

    public int size() {
        return items_stored;
    }

    /**
     * Inner array always 2 times bigger, than amount of items currently stored
     * When to resize determined by free space left:
     * - when no space left - make new inner array 2 times bigger
     * - when current items count is 1/4 of inner array length, copy all to new array which is just 2 times bigger than items amt
     */
    private void resizeArray() {
        int newSize = items_stored == 0 ? 1 : items_stored * 2;
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
        lst = items_stored;
        data = resized;
    }

    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (lst == data.length) resizeArray();
        data[lst++] = item;
        items_stored++;
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
        items_stored--;
        if (!isEmpty()) fst++;
        if (it == null) throw new IllegalStateException("Idx = " + idx + " items_stored = " + items_stored);
        if (data.length / 4 >= items_stored) resizeArray();
        return it;
    }

    public Item sample() {
        if (items_stored == 0) throw new NoSuchElementException();
        return data[getRandomIndex()];
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator {
        private int iterFst, iterLst;
        private Item[] iterData;
        private int iterCnt;

        private DequeIterator() {
            iterData = (Item[]) new Object[items_stored];
            for (int i = fst, k = 0; i < lst; i++, k++) {
                iterData[k] = data[i];
            }
            iterFst = 0;
            iterLst = iterData.length;
            iterCnt = items_stored;
        }

        @Override
        public boolean hasNext() {
            return iterCnt != 0;
        }

        @Override
        public Item next() {
            if (!DequeIterator.this.hasNext()) throw new NoSuchElementException();
            int idx = StdRandom.uniform(iterFst, iterLst);
            Item it;
            if (idx == iterFst) {
                it = iterData[iterFst];
            } else {
                it = iterData[idx];
                iterData[idx] = iterData[iterFst];
            }
            iterData[iterFst] = null;
            iterFst++;
            iterCnt--;
            return it;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}