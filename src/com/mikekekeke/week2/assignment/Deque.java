package com.mikekekeke.week2.assignment;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class Deque<Item> {

    private Node first;
    private Node last;
    private int cnt;

    private class Node {
        private Item value;
        private Node next;
        private Node prev;
        Node(Item value) {Node.this.value = value;}
    }

    public Deque() {
        first = null;
        last = null;

    }

    private void onAdd(){
        cnt++;
    }

    private void onRemove(){
        if(cnt == 0) throw new IllegalStateException("Can't rem from empty");
        cnt--;
    }

    public boolean isEmpty() {
        if ((first == null && last != null) || (first != null && last == null)) throw new IllegalStateException();
        return first == null && last == null;
    }

    public int size() {
        return cnt;
    }

    public void addFirst(Item item) {
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
        onAdd();

    }

    public void addLast(Item item) {
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
        onAdd();
    }

    public Item removeFirst() {
        Item value = first.value;
        first = first.next;
        first.prev = null;
        onRemove();
        return value;
    }

    public Item removeLast() {
        Item value = last.value;
        last = last.prev;
        last.next = null;
        onRemove();
        return value;
    }

    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            Node current = first;
            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Item next() {
                Item value = current.value;
                current = current.next;
                return value;
            }
        };
    }

    public static void main(String[] args) {
        Deque<Integer> d1 = new Deque<>();
        for (int i = 0; i < 5; i++) {
            try { d1.addFirst(i);} catch (Exception e) {StdOut.println("Step: " + i); throw e;}
        }
        Iterator<Integer> it = d1.iterator();
        while (it.hasNext()) {
            StdOut.println(it.next());
        }
    }
}
