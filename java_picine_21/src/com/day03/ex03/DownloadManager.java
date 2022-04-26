package com.day03.ex03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.Semaphore;

public class DownloadManager {
    private String inputPath;
    private final int MAXTHREAD;
    private Set<String> links;
    private Semaphore semaphore;
    private Downloader[] threads;

    public DownloadManager(String inputPath, int count) {
        this.inputPath = inputPath;
        MAXTHREAD = count;
        links = new TreeSet<>();
        semaphore = new Semaphore(count);
        try {
            loadLinks();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadLinks() throws IOException{
        try(BufferedReader br = new BufferedReader(new FileReader(inputPath))){
            String str;
            while ((str = br.readLine()) != null){
                if (str.matches("http*\\\\*+.*+"))
                    links.add(str);
            }
        }
    }

    private Downloader[] initThreads(){
        threads = new Downloader[MAXTHREAD];
        for (int i = 0; i < MAXTHREAD; i++) {
            threads[i] = new Downloader(semaphore);
            threads[i].start();
        }
        return threads;
    }

    private Downloader getWaitingThread(){
        for (Downloader d: threads) {
            if (d.getState() == Thread.State.TIMED_WAITING || d.getState() == Thread.State.WAITING)
                return d;
        }
        return null;
    }


    public void downloadAll() throws InterruptedException {
        initThreads();
        Downloader available;
        Iterator<String> it = links.iterator();
        int i = 0;
        while (it.hasNext()){
            if (semaphore.tryAcquire())
            {
                if ((available = getWaitingThread()) != null){
                    System.out.println("setting file");
                    available.setOutputName("src/com/day03/ex03/outfile_" + i);
                    available.setLink(it.next());
                }
                semaphore.release();
            }
        }

        i = 0;
        while (i < MAXTHREAD){
            if (semaphore.tryAcquire())
            {
                if ((available = getWaitingThread()) != null){
                    available.setOutputName("");
                    available.setLink("42");
                    i++;
                }
            }
        }
    }



}
