package com.mikekekeke.week_1;

interface QU {
    void union(int a, int b);

    boolean connected(int a, int b);
}

class QuickFindUF implements QU {

    private int[] id;

    public QuickFindUF(int n) {
        id = new int[n];
        for (int i = 0; i < id.length; i++) {
            id[i] = i;
        }
    }

    public void union(int a, int b) {
        int newId = id[a];
        int oldId = id[b];
        for (int i = 0; i < id.length; i++) {
            if (id[i] == oldId) {
                id[i] = newId;
            }
        }

    }

    public boolean connected(int a, int b) {
        return id[a] == id[b];
    }
}

class QuickUnionUF implements QU {

    private int[] id;

    public QuickUnionUF(int n) {
        id = new int[n];
        for (int i = 0; i < id.length; i++) {
            id[i] = i;
        }
    }

    private int getRootOf(int x) {
        // shorter from lecture
//        while (x != id[x]) x = id[x];
//        return x;

        // my
        int tempRoot = x;
        while (id[tempRoot] != tempRoot) {
            tempRoot = id[tempRoot];
        }
        return tempRoot;
    }

    public void union(int a, int b) {
        int aRoot = getRootOf(a);
        int bRoot = getRootOf(b);
        id[aRoot] = bRoot;
    }

    public boolean connected(int a, int b) {
        return getRootOf(a) == getRootOf(b);
    }
}


class QuickUnionUFW implements QU {

    private int[] id;
    private int[] size;

    public QuickUnionUFW(int n) {
        id = new int[n];
        size = new int[n];
        for (int i = 0; i < id.length; i++) {
            id[i] = i;
            size[i] = 1;
        }
    }

    private int getRoot(int x) {
        while (x != id[x]) {
            id[x] = id[id[x]]; // (almost) flattening tree
            x = id[x];
        }
        return x;
    }

    public void union(int a, int b) {
        int aRoot = getRoot(a);
        int bRoot = getRoot(b);
        if (aRoot == bRoot) return;
        if (size[aRoot] > size[bRoot]) {
            id[bRoot] = aRoot;
            size[aRoot] += size[bRoot];
        } else {
            id[aRoot] = bRoot;
            size[bRoot] += size[aRoot];
        }
    }

    public boolean connected(int a, int b) {
        return getRoot(a) == getRoot(b);
    }
}

class QuickUnionFind implements QU {

    private int[] id;
    private int[] size;
    private int[] maximum;

    public QuickUnionFind(int n) {
        id = new int[n];
        size = new int[n];
        maximum = new int[n];
        for (int i = 0; i < id.length; i++) {
            id[i] = i;
            size[i] = 1;
            maximum[i] = i;
        }
    }

    private int getRoot(int x) {
        while (x != id[x]) {
            id[x] = id[id[x]];
            x = id[x];
        }
        return x;
    }

    public void union(int a, int b) {
        int aRoot = getRoot(a);
        int bRoot = getRoot(b);

        int max = Math.max(maximum[aRoot], maximum[bRoot]);
        if (aRoot == bRoot) return;
        if (size[aRoot] > size[bRoot]) {
            id[bRoot] = aRoot;
            size[aRoot] += size[bRoot];
            maximum[aRoot] = max;
        } else {
            id[aRoot] = bRoot;
            size[bRoot] += size[aRoot];
            maximum[bRoot] = max;
        }
    }

    public boolean connected(int a, int b) {
        return getRoot(a) == getRoot(b);
    }

    public int find(int a) {
        return maximum[getRoot(a)];
    }
}
