package com.day03.ex03;

public class Program {
    public static void main(String[] args) {
        if (args.length < 1){
            System.err.println("wrong number of arguments");
            System.exit(-1);
        }
        if (!args[0].matches("--threadsCount=[0-9]+")) {
            System.err.println("Wrong argument format");
            System.exit(-1);
        }
        int count = Integer.parseInt(args[0].split("=")[1]);
        if (count < 1){
            System.err.println("wrong threads count");
            System.exit(-1);
        }

        DownloadManager dm = new DownloadManager("src\\com\\day03\\ex03\\files_urls.txt", count);
        try {
            dm.downloadAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
