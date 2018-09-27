package com.mikekekeke.week2.assignment;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayDeque<Item> implements Iterable<Item> {
    private Item[] inner;
    private int front;
    private int back;
    private int first;
    private int last;
    private int count;

    public ArrayDeque() {
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


    public String show() {
        return Arrays.toString(inner) + " / "
                + inner.length +
                " / cnt: " + count +
                " / fr: " + front +
                " / ba: " + back +
                " / fst: " + first + "(" + inner[first+1] +")" +
                " / lst: " + last + "(" + inner[last-1] +")";
    }

    private void check(boolean condition) {
        if (!condition) throw new IllegalStateException("Bad");
    }

    public static void main(String[] args) {
        ArrayDeque<String> d = new ArrayDeque<>();
        StdOut.println(d.isEmpty());
        int fadd = 2;
        for (int i = 1; i <= fadd; i++) {
            d.addFirst("" + i);
            StdOut.println(d.show());
        }

        int badd = 10;
        for (int i = 1; i <= badd; i++) {
            d.addLast("-" + i);
            StdOut.println(d.show());
        }

        int frem = 12;
        for (int i = 1; i <= frem; i++) {
            String f = d.removeFirst();
            StdOut.println(f);
            StdOut.println(d.show());
        }

//        int brem = 12;
//        for (int i = 1; i <= brem; i++) {
//            d.removeLast();
//            StdOut.println(d.show());
//        }

        ArrayDeque<Integer> d2 = new ArrayDeque<>();
        int[] in = new int[] {1,2,3,4,5};
        for (int i = 0; i < in.length; i++) {
            d2.addFirst(in[i]);
        }
        int[] out = new int[in.length];
        for (int i = 0; i < in.length; i++) {
            out[i] = d2.removeLast();
        }

        StdOut.println(Arrays.equals(in, out));

    }


}
