package edu.school21.chat;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

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

            UsersRepositoryJdbcImp users = new UsersRepositoryJdbcImp(dataSource);

            List<User> found = users.findAll(0,3);
            for (User u: found) {
                System.out.println(u);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}

/*
db.driver = org.postgresql.Driver
db.url = jdbc:postgresql://localhost:5432/java_picine_day_05
db.user = postgres
db.password = @Abe29
*/