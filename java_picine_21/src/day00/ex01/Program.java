package day00.ex01;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        checkPrime(number);
    }

    public static void checkPrime(int n){
        int check;

        if (n < 2){
            System.err.println("IllegalArgument ");
            System.exit(-1);
        }

        for (check = 2; check * check < n + 1 ; check++) {
            if (n % check == 0){
                System.out.println("false  " + (check - 1));
                return;
            }
        }
        System.out.println("true  " + (check - 1));
    }

}
