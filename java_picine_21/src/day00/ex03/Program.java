package day00.ex03;

import java.util.*;

public class Program {
    public static void main(String[] args) {
        showData(collectData());
    }

    public static List<Integer> collectData() {
        Scanner scanner = new Scanner(System.in);
        List<Integer> data = new ArrayList<>(18);
        String input;
        int min;

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
            data.add(min);
            scanner.nextLine();
        }
        return data;
    }

    public static void showData(List<Integer> dict){
        int w = 1;
        for (int i : dict) {
            System.out.print("Week " + w++ + " ");
            for (int j = 0; j < i; j++) {
                System.out.print("=");
            }
            System.out.println(">");
        }
    }
}
