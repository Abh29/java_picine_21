package edu.school21.sockets.app;

import edu.school21.sockets.config.SocketsApplicationConfig;
import edu.school21.sockets.server.Server;
import edu.school21.sockets.services.UsersService;
import edu.school21.sockets.services.UsersServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

        if (args.length != 1)
        {
            System.err.println("Wrong number of arguments");
            System.exit(-1);
        }

        int port = Integer.parseInt(args[0]);

        ApplicationContext context = new AnnotationConfigApplicationContext(SocketsApplicationConfig.class);
        UsersService usersService = context.getBean(UsersService.class);
        Server server = new Server(port);
        server.setUsersService(usersService);
        server.start();
    }
}
