package com.day03.ex03;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.concurrent.Semaphore;

public class Downloader extends Thread {

    private String link;
    private String outputName;
    private Semaphore semaphore;

    Downloader(Semaphore semaphore){
        this.semaphore = semaphore;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setOutputName(String outputName) {
        this.outputName = outputName;
    }

    private void downloadLink() throws InterruptedException {
        semaphore.acquire();
        try (BufferedInputStream in = new BufferedInputStream(new URL(link).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(outputName)) {
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
            link = null;
            outputName = null;
        } catch (Exception e) {
            System.err.println("could not download from link " + link);
        }finally {
            semaphore.release();
            this.wait();
        }
    }

    @Override
    public void run() {
        System.out.println("thread started");
        while (true)
        {
            try {
                while (link == null) {
                   sleep(1000);
                }
                if (link.equals("42"))
                    break;
                else {
                    downloadLink();
                }
            } catch (InterruptedException e) {
                    e.printStackTrace();
            }
        }
    }
}
