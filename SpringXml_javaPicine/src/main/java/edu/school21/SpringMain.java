package edu.school21;


import edu.school21.interfaces.Printer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context.xml");
        Printer printer = applicationContext.getBean("printerWithPrefix", Printer.class);
        printer.print("Hello!");
    }
}
