package com.mikekekeke.week2;

import java.util.Arrays;

public class StringStack {
    private String[] data;
    private int top;

    public StringStack() {
        data = new String[1];
        top = 0;
    }
    
    private void resize(int size) {
        String[] resized = new String[size];
        for (int i = 0; i < top; i++) {
            resized[i] = data[i];
        }
        data = resized;
    }

    private void grow(){
        String[] biggerData = new String[data.length * 2];
        for (int i = 0; i < data.length; i++) {
            biggerData[i] = data[i];
        }
        data = biggerData;
    }

    private void shrink(){
        String[] smallerData = new String[data.length / 2]; // (/2) !!!
        for (int i = 0; i < smallerData.length; i++) {
            smallerData[i] = data[i];
        }
        data = smallerData;
    }

    public void push(String string) {
//        if (top >= data.length) grow();
        if (top >= data.length) resize(data.length * 2);
        data[top] = string;
        top++;
    }

    public String pop(){
        if (top-1 < 0) throw new IllegalStateException("Empty stack");
        top--;
        String s = data[top];
        data[top] = null;
//        if (top > 0 && top <= data.length / 4) shrink();
        if (top > 0 && top <= data.length / 4) resize(data.length / 2);
        return s;
    }

    public String peek(){
        if (top - 1 < 0) throw new IllegalStateException("Empty stack");
        return data[top - 1];
    }

    @Override
    public String toString() {
        return Arrays.toString(data);
    }
}
