package day00.ex05;

import java.util.Scanner;


public class Program {

    static int nbStudents = 0;
    static int nbClasses = 0;

    static String[] students;
    static String[] september2020 = {"TUE", "WE", "THU", "FRI", "SAT", "SUN", "MO"};
    static byte[][][] timeTable;

    public static void main(String[] args) {
        students = new String[10];


        Scanner scanner = new Scanner(System.in);
        String input;
        int hour = 0;

        while(true)
        {
            input = scanner.nextLine();
            if (input.equals("."))
                break;
            if (input.length() > 10)
                System.err.println("name length should not be more than 10");
            else
                addStudent(input);
        }

        timeTable = new byte[30][6][nbStudents + 1];

        while(true)
        {

            if (scanner.hasNextInt())
                hour = scanner.nextInt();
            input = scanner.next();
            if (input.equals("."))
                break;
            addClass (hour, input);
            scanner.nextLine();
        }

        while(true){
            String name = scanner.next();
            if (name.equals("."))
                break;
            hour = scanner.nextInt();
            int day = scanner.nextInt();
            input = scanner.next();

            addToTimeTable(name, hour, day, input);
            scanner.nextLine();
        }

        showTimeTable();

    }

    public static void addStudent(String name){
        if (students.length == nbStudents)
        {
            String[] tmp = new String[nbStudents * 2 + 1];
            for (int i = 0; i < students.length; i++) {
                tmp[i] = students[i];
            }
            students = tmp;
        }
        students[nbStudents++] = name;
    }

    public static void addClass(int hour, String day){
       if (nbClasses >= 10)
           System.err.println("There Should be no more than 10 classes in the week!");
       else
       {
           int d = indexOf(september2020, day);
           if (d == -1)
               return ;
           while (d < 30)
           {
               timeTable[d][hour - 1][0] = 1;
               d += 7;
           }
           nbClasses++;
       }
    }

    public static void addToTimeTable(String name, int hour, int day, String presence){
        if (day > 30 || hour > 5 || indexOf(students, name) == -1)
            return;
        timeTable[day - 1][hour - 1][indexOf(students, name) + 1] = (byte) (presence.equals("HERE") ? 1 : -1);
    }

    public static void showTimeTable()
    {
        System.out.print("           ");
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 6; j++) {
                if (timeTable[i][j][0] == 1){
                    System.out.printf("%d:00%3s%3d|", j + 1, september2020[i % 7], i + 1);
                }
            }
        }

        for (int i = 0; i < nbStudents; i++) {
            System.out.printf("\n%10s|", students[i]);
            for (int j = 0; j < 30; j++) {
                for (int k = 0; k < 6; k++) {
                    if (timeTable[j][k][0] == 1 && timeTable[j][k][i + 1] == 0)
                        System.out.print("          |");
                    else if (timeTable[j][k][0] == 1)
                        System.out.printf("%10d|", timeTable[j][k][i + 1]);
                }
            }
        }
        System.out.println();
    }


    public static int indexOf(String[] arr, String elm){
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(elm))
                return i;
        }
        return -1;
    }

}
