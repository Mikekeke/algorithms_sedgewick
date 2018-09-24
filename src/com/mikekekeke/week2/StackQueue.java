package com.mikekekeke.week2;

import java.util.Stack;

public class StackQueue<T> {
    private Stack<T> enqStack = new Stack<>();
    private Stack<T> deqStack = new Stack<>();

    public void enqueue(T item) {
        enqStack.push(item);
    }

    public T dequeue() {
        if (deqStack.isEmpty()) {
            while (!enqStack.empty()) deqStack.push(enqStack.pop());
        }
        if (deqStack.isEmpty()) throw new IllegalStateException("Empty queue");
        return deqStack.pop();
    }
}
