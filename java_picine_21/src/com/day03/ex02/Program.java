package com.day03.ex02;

import java.math.BigInteger;
import java.util.Random;

public class Program {
    public static void main(String[] args) {
        if (args.length < 2){
            System.err.println("wrong number of arguments");
            System.exit(-1);
        }
        if (!args[0].matches("--arraySize=[0-9]+") || !args[1].matches("--threadsCount=[0-9]+")) {
            System.err.println("Wrong argument format");
            System.exit(-1);
        }


        int count = Integer.parseInt(args[1].split("=")[1]);
        int size = Integer.parseInt(args[0].split("=")[1]);

        if (size < 1 || count < 1) {
            System.err.println("arguments can not be negative");
            System.exit(-1);
        }

        if (size > 2000000){
            System.err.println("too big array size");
            System.exit(-1);
        }
        if (count > size) {
            System.err.println("the number of threads can not be bigger than the size of the array");
            System.exit(-1);
        }

        int[] numbers = randomArray(size);

        BigInteger sum = sumArray(numbers);
        System.out.println("sum: " + sum);

        ArraySumThread[] threads = threadsArray(numbers, count);

        startAll(threads);
        joinAll(threads);

        sum = BigInteger.ZERO;

        for (ArraySumThread th: threads) {
            sum = sum.add(th.getSum());
        }

        System.out.println("sum: " + sum);

    }

    static BigInteger sumArray(int[] arr){
        BigInteger sum = BigInteger.ZERO;
        for (int j : arr) {
            sum = sum.add(BigInteger.valueOf(j));
        }
        return sum;
    }

    static int[] randomArray(int size){
        int[] out = new int[size];
        Random rnd = new Random();

        for (int i = 0; i < size; i++) {
            out[i] = rnd.nextInt(1000);
        }
        return out;
    }

    static ArraySumThread[] threadsArray(int[] arr, int count){
        ArraySumThread[] threads = new ArraySumThread[count];
        int start, end;

        for (int i = 0; i < count; i++) {
            start = i * arr.length / count;
            end = i == count - 1 ? arr.length : (i + 1) * arr.length / count;
            threads[i] = new ArraySumThread(i + 1, arr, start, end);
        }
        return threads;
    }

    static void startAll(Thread[] threads){
        for (Thread th: threads) {
            th.start();
        }
    }

    static void joinAll(Thread[] threads){
        for (Thread th: threads) {
            try {
                th.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
