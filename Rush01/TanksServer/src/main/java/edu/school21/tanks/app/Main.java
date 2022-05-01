package edu.school21.tanks.app;

import edu.school21.tanks.config.SocketsApplicationConfig;
import edu.school21.tanks.server.Server;
import edu.school21.tanks.services.GamesService;
import edu.school21.tanks.services.UsersService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

        if (args.length != 1)
        {
            System.err.println("Wrong number of arguments!");
            System.exit(-1);
        }

        if (!args[0].matches("port=[0-9]+")){
            System.err.println("Wrong argument format!");
            System.exit(-1);
        }

        int port = Integer.parseInt(args[0].split("=")[1]);

        ApplicationContext context = new AnnotationConfigApplicationContext(SocketsApplicationConfig.class);
        UsersService usersService = context.getBean(UsersService.class);
        GamesService gamesService = context.getBean(GamesService.class);
        Server server = new Server(port, 2);
        server.setUsersService(usersService);
        server.setGamesService(gamesService);
        server.start();
        try {
            server.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
            server.interrupt();
        }

        System.out.println("server out!");
    }
}
