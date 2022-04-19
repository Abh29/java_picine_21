package day00.ex02;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number;
        int count = 0;

        while (true)
        {
            if (scanner.hasNextInt())
            {
                number = scanner.nextInt();
                if (number == 42)
                    break;
                if (isPrime(sumDigits(number)))
                    count++;
            }else if(scanner.hasNext())
                scanner.next();
        }
        System.out.println("Count of coffee-request - " + count);
    }

    public static boolean isPrime(int n){
        if (n < 2)
            return false;
        int i;
        for (i = 2; i * i < n + 1; i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

    public static int sumDigits(int n) {
        int out;

        if (n < 0)
            n *= -1;
        for (out = 0; n > 0 ; out += n % 10, n /= 10){}
        return out;
    }
}
