package edu.school21.chat;

import java.io.IOException;
import java.io.InputStream;
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

            System.out.println("Enter the author ID");
            long authorId = scanner.nextLong();
            System.out.println("Enter the chatroom ID");
            long roomId = scanner.nextLong();

            scanner.nextLine();
            System.out.println("Enter the message text");
            String text = scanner.nextLine();

            long id = messages.saveMessage(authorId, roomId, text);
            System.out.println("message id: " + id);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
