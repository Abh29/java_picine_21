package com.day03.ex00;

import java.util.concurrent.CountDownLatch;

public class Program {
    public static void main(String[] args) {
        if (args.length < 1){
            System.err.println("wrong number of arguments");
            System.exit(-1);
        }
        if (!args[0].matches("--count=[0-9]+"))
        {
            System.err.println("Wrong number format");
            System.exit(-1);
        }

        int count = Integer.parseInt(args[0].split("=")[1]);

        CountDownLatch latch = new CountDownLatch(1);
        ShouterThread t1 = new ShouterThread("HEN", count, latch);
        ShouterThread t2 = new ShouterThread("EGG", count, latch);


        t1.start();
        t2.start();

        try{
            latch.countDown();
            t1.join();
            t2.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        for (int i = 0; i < count; i++) {
            System.out.println("HUMAN");
        }

    }
}
