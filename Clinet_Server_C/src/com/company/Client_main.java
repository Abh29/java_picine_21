package com.company;

import java.io.IOException;
import java.util.Scanner;

public class Client_main {

    static final String LOCALHOST = "127.0.0.1";
    public static void main(String[] args) {
	    Client client = new Client();
        Scanner in = new Scanner(System.in);
        String msg;
        try
        {
            client.start(LOCALHOST, 6655);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        while (true)
        {
            msg = in.nextLine();
            if (msg.equals("exit"))
                break;
            try {
                client.sendMsg(msg);
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
        client.close();
    }
}
