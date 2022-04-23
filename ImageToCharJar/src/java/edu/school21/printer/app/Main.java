package edu.school21.printer.app;


import edu.school21.printer.logic.MyBMPPrinter;
import edu.school21.printer.logic.MyBMPReader;

public class Main {
    public static void main(String[] args) {

        if (args.length != 2)
        {
            System.err.println("Wrong number of arguments");
            System.exit(-1);
        }
        if (!args[0].matches("--white=.") || !args[1].matches("--black=."))
        {
            System.out.println("wrong parameter format");
            System.exit(-1);
        }

        char white = args[0].split("=")[1].charAt(0);
        char black = args[1].split("=")[1].charAt(0);

        MyBMPReader reader = MyBMPReader.read("src\\resources\\image.bmp");
        if (reader == null)
            return;

        MyBMPPrinter printer = new MyBMPPrinter(reader, white, black);

        System.out.println(printer);

    }
}
