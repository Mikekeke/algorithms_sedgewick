package com.mikekekeke.week_1;

class QuickFindUF {

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

class QuickUnionUF {

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
