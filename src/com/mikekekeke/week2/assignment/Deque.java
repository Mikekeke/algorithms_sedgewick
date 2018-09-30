package com.mikekekeke.week2.assignment;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int n;

    public Deque() {
        first = null;
        last = null;

    }

    public static void main(String[] args) {
        Deque<String> deque2 = new Deque<>();
        Iterator<String> it = deque2.iterator();
        for (int i = 0; i < 2 ; i++) {
            deque2.addFirst("fff" + i);
        }
        for (int i = 0; i < 10 ; i++) {
            StdOut.println("call");
            StdOut.println(it.next());
        }
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();
        Node newNode = new Node(item);
        if (isEmpty()) {
            first = newNode;
            last = newNode;
        } else {
            Node oldFst = first;
            oldFst.prev = newNode;
            newNode.next = oldFst;
            first = newNode;
        }
        n++;
    }

    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        Node newNode = new Node(item);
        if (isEmpty()) {
            first = newNode;
            last = newNode;
        } else {
            Node oldLast = last;
            newNode.prev = oldLast;
            oldLast.next = newNode;
            last = newNode;
        }
        n++;
    }

    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        n--;
        Item value = first.value;
        if (isEmpty()) {
            first = null;
            last = null;
        } else {
            first = first.next;
            first.prev = null;
        }
        return value;
    }

    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        n--;
        Item value = last.value;
        if (isEmpty()) {
            first = null;
            last = null;
        } else {
            last = last.prev;
            last.next = null;
        }
        return value;
    }

    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            Node current = null;
            int iterCount = 0;

            @Override
            public boolean hasNext() {
                return n > iterCount;
            }

            @Override
            public Item next() {
                if (iterCount == 0) {
                    current = first;
                }
                iterCount++;
                if (iterCount > n) throw new NoSuchElementException();
                Item value = current.value;
                current = current.next;
                return value;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    private class Node {
        private Item value;
        private Node next;
        private Node prev;

        Node(Item value) {
            Node.this.value = value;
        }
    }
}
