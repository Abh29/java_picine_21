package com.company;

import java.util.Scanner;

public class Server_main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Server server = new Server(6655);
        server.start();
        while (true) {
            if (in.nextLine().equals("exit")) {
                server.interrupt();
                break;
            }
        }
    }
}
