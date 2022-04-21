package com.day02.ex01;

import java.io.IOException;

public class Program {
    public static void main(String[] args) {
        if (args.length < 2)
        {
            System.err.println("wrong arguments number");
            return;
        }

        try{
            FilesComparator fc = new FilesComparator(args[0], args[1]);
            System.out.println("claculating similartiy between " + args[0] + " and " + args[1]);
            System.out.println(fc.getSimilarity());

        }catch (IOException e){
            e.printStackTrace();
        }


    }
}
