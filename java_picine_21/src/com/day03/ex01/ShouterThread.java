package com.day03.ex01;

public class ShouterThread extends Thread{

    private final String MSG;
    private final int COUNT;
    private final Object LOCK;

    ShouterThread(String msg, int count, Object lock){
        MSG = msg;
        COUNT = Math.max(count, 0);
        LOCK = lock;
    }

    @Override
    public void run() {
        synchronized (LOCK) {
            try {
                for (int i = 0; i < COUNT; i++) {

                        System.out.println(MSG);
                        LOCK.notify();
                        LOCK.wait();
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                LOCK.notify();
            }
        }
    }
}
