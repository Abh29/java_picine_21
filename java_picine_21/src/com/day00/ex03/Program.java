package com.day00.ex03;


import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        showData(collectData());
    }

    public static String[] collectData() {
        Scanner scanner = new Scanner(System.in);
        String input;
        String[] out = new String[18];
        int min, w = 0;

        for (int i = 1; i < 19; i++) {
            input = scanner.nextLine();
            if (input.equals("42"))
                break;
            if (!input.equals("Week " + i)){
                System.err.println("IllegalArgument");
                System.exit(-1);
            }
            min = 10;
            for (int j = 0; j < 5 && scanner.hasNextInt(); j++) {
                int tmp = scanner.nextInt();
                if (tmp > 9 || tmp < 1)
                {
                    System.err.println("IllegalArgument");
                    System.exit(-1);
                }
                min = min > tmp ? tmp : min;
            }
            out[w] = "";
            for (int j = 0; j < min; j++) {
                out[w] += "=";
            }
            out[w++] += ">";
            scanner.nextLine();
        }
        return out;
    }

    public static void showData(String data[]){
        int w = 1;

        for (String s: data) {
            if (s != null)
            {
                System.out.print("Week " + w++ + " ");
                System.out.println(s);
            }
        }
    }
}
