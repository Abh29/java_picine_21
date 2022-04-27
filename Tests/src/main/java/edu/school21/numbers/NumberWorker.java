package edu.school21.numbers;

public class NumberWorker {

    public boolean isPrime(int number){
        if (number < 2)
            throw new IllegalArgumentException(number + " < 2");

        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0)
                return false;
        }
        return true;
    }

    public int digitsSum(int number){
        if (number == 0)
            return 0;
        if (number == Integer.MIN_VALUE)
            return 47;

        number = number < 0 ? -number : number;

        int out = 0;

        while (number > 0){
            out += number % 10;
            number /= 10;
        }
        return out;
    }

}
