package edu.school21.chat;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        SimpleDataSource dataSource = null;

        try(InputStream input = Program.class.getClassLoader().getResourceAsStream("application.properties")){

            if (input == null){
                System.err.println("could not read properties file!");
                System.exit(-1);
            }

            Properties properties = new Properties();
            properties.load(input);

            dataSource = new SimpleDataSource(properties);


        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        try {
            MessagesRepositoryJdbcImp messages = new MessagesRepositoryJdbcImp(dataSource);
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter a message id to update :");
            long id = scanner.nextLong();
            scanner.nextLine();

            Optional<Message> message = messages.findById(id);
            if (!message.isPresent()){
                System.err.println("no message id found ! " + id);
                System.exit(-1);
            }

            System.out.println(message);

            System.out.println("Enter the new message text");
            String text = scanner.nextLine();

            message.get().setText(text);

            messages.updateMessage(message.get());

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
