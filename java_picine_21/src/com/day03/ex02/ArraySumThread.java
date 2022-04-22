package com.day03.ex02;

import java.math.BigInteger;

public class ArraySumThread extends Thread{

    private int[] arr;
    private int start;
    private int end;
    private int index;
    private BigInteger sum;

    ArraySumThread(int index, int[] arr, int start, int end){
        this.arr = arr;
        this.start = start;
        this.end = end;
        this.index = index;
        sum = BigInteger.ZERO;
    }

    @Override
    public void run() {
        sumArraySection();
    }

    private void sumArraySection(){
        for (int i = start ; i < end; i++) {
            sum = sum.add(BigInteger.valueOf(arr[i]));
        }
    }

    public BigInteger getSum() {
        System.out.println("Thread " + index + ": form " + start + " to " + end + " sum: " + sum);
        return sum;
    }
}
