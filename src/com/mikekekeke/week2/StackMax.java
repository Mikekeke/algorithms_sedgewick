package com.mikekekeke.week2;

import java.util.Stack;

public class StackMax extends Stack<Integer> {
    private Stack<Integer> max = new Stack<>();

    @Override
    public Integer push(Integer item) {
        if (max.isEmpty() || item > max.peek()) max.push(item);
        else max.push(max.peek());
        return super.push(item);
    }

    @Override
    public synchronized Integer pop() {
        max.pop();
        return super.pop();
    }

    public synchronized Integer max() {
        return max.peek();
    }
}
