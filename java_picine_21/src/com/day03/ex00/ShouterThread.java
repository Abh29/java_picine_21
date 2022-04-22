package com.day03.ex00;

import java.util.concurrent.CountDownLatch;

public class ShouterThread extends Thread{

    private final String MSG;
    private final int COUNT;
    private final CountDownLatch LATCH;

    ShouterThread(String msg, int count, CountDownLatch latch){
        MSG = msg;
        COUNT = Math.max(count, 0);
        LATCH = latch;
    }

    @Override
    public void run() {
        try {
            LATCH.await();
            for (int i = 0; i < COUNT; i++) {
                System.out.println(MSG);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
