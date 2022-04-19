package day00.ex04;

import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        if (!input.equals(""))
            showDatagram(countLetters(input.toCharArray()));
    }

    public static int[][] countLetters(char[] str) {
        String unique = "";

        for (char c : str) {
            if (getIndex(unique.toCharArray(), c) == -1)
                unique += c;
        }

        int[][] alphabets = new int[2][unique.length()];
        for (char c : str) {
            alphabets[0][getIndex(unique.toCharArray(), c)] = c;
            alphabets[1][getIndex(unique.toCharArray(), c)]++;
        }

        sortDictionary(alphabets);
        return alphabets;
    }

    public static void showDatagram(int[][] count){

        int[][] buff = new int[12][10];
        int max = 0, min = count[0].length > 10 ? 9 : count[0].length - 1;

        max = count[1][max];
        min = count[1][min];


        for (int i = 0; i < 10 && i < count[0].length; i++) {
            buff[11][i] = count[0][i];
            int height = (int)((10f * count[1][i]) / max);
            buff[10 - height][i] = count[1][i];
            for (int j = 0; j < height ; j++) {
                buff[10 - j][i] = -1;
            }
        }

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 10 && j < count[0].length; j++) {
                if (i == 11)
                    System.out.print(" " + (char) buff[i][j] + " ");
                else if (buff[i][j] == -1)
                    System.out.print(" # ");
                else if (buff[i][j] == 0)
                    System.out.print("  ");
                else
                    System.out.printf("%2d ", buff[i][j]);
            }
            System.out.println();
        }

    }

    static int getIndex(char[] chars, char c){

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == c)
                return i;
        }
        return -1;
    }

    static void sortDictionary(int[][] arr){
        for (int i = 0; i < arr[1].length; i++) {
            for (int j = i; j < arr[1].length; j++) {
                if (arr[1][i] < arr[1][j]){
                    swipe(arr[0], i, j);
                    swipe(arr[1], i, j);
                }else if (arr[1][i] == arr[1][j] && arr[0][i] > arr[0][j]){
                    swipe(arr[0], i, j);
                    swipe(arr[1], i, j);
                }
            }
        }    
    }

    static void swipe(int[] arr, int i, int j)
    {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}
/*
* AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAASSSSSSSSSSSSSSSSSSSSSSSSDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDWEWWKFKKDKKDSKAKLSLDKSKALLLLLLLLLLRTRTETWTWWWWWWWWWWOOOOOOO42
* */

