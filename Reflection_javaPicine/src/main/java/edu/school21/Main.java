package edu.school21;

import edu.school21.logic.ClassesWorker;

import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ClassesWorker worker = null;
        Map<String, Class> classMap = null;
        Scanner scanner = new Scanner(System.in);

        try {
            worker = new ClassesWorker("edu.school21.classes");
            classMap = worker.getClassMap();
            for (String s: classMap.keySet()) {
                System.out.println(s);
            }

            System.out.println("---------------------");

            System.out.println("Enter Class name:");
            String className = scanner.nextLine();
            Class<Object> myClass = worker.printClassInfo(className);

            System.out.println("---------------------");

            System.out.println("Let's create an object.");
            Object o = worker.getInstanceOf(myClass);
            System.out.print("object created: ");
            System.out.println(o);

            System.out.println("---------------------");

            System.out.println("Enter name of the field for changing:");
            String fieldName = scanner.nextLine();
            worker.updateField(o, fieldName);
            System.out.println(o);

            System.out.println("---------------------");

            System.out.println("Enter name of the method for call:");
            String methodName = scanner.nextLine();
            worker.callMethod(o, methodName);

        }catch (Exception e){
            e.printStackTrace();
            System.exit(-1);
        }





    }
}
