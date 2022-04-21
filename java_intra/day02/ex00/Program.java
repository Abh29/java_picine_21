package com.day02.ex00;

import java.io.IOException;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {


        try (SignatureWorker sw = new SignatureWorker("src\\com\\day02\\ex00\\signatures.txt")) {

            String str;
            Scanner sc = new Scanner(System.in);
            sw.openOutputFile("src\\com\\day02\\ex00\\result.txt");

            while(true){
                str = sc.nextLine();
                if (str.equals("42"))
                    break;
                sw.identifyFileToOutput(str);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("unknown exception");
            e.printStackTrace();
        }


    }
}

// C:\Users\acer\Pictures\splay2.png
//C:\Users\acer\Pictures\Pictures.rar
//C:\Users\acer\Pictures\gifs\giphy.gif
//C:\Users\acer\Pictures\gifs\teamBG.jpg