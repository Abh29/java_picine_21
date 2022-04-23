package com.day02.ex02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
      /*  if (args.length < 1){
            System.err.println("Wrong number of arguments");
            System.exit(-1);
        }
        String[] t = args[0].split("=");
        if (t.length < 2 || !t[0].equals("--current-folder")){
            System.err.println("wrong parameter " + args[0]);
            System.exit(-1);
        }*/

        String input = "";
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {

                    input = scanner.nextLine();
                    if (input.equals("42"))
                        break;
                    Process p = Runtime.getRuntime().exec(input);
                    p.waitFor();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
                    String line=reader.readLine();

                    while (line != null) {
                        System.out.println(line);
                        line = reader.readLine();
                    }

            }catch (Exception e){
                System.err.println("wrong input cmd " + input);
            }
        }
    }
}