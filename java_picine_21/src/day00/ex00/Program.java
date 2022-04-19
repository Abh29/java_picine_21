package day00.ex00;

public class Program {

    public static void main(String[] args) {
        int number = 479598;

        System.out.println(sumDigits(number));
    }

    public static int sumDigits(int n) {

        int out = n % 10;
        n /= 10;
        out += n % 10;
        n /= 10;
        out += n % 10;
        n /= 10;
        out += n % 10;
        n /= 10;
        out += n % 10;
        n /= 10;
        out += n % 10;
        return (out);
    }
}
