package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class Client {
    private Socket client;
    private PrintWriter out;
    private BufferedReader in;

    public void start(String ip, int port) throws IOException {
        client = new Socket(ip, port);
        out = new PrintWriter(client.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
    }

    public void sendMsg(String msg) throws IOException {
        long pid = ProcessHandle.current().pid();
        out.printf("%-10d - %s\n", pid, msg);
        String resp = in.readLine();
        if (!resp.isEmpty())
            System.out.println(resp);
    }

    public void close() {
        try {
            in.close();
            out.close();
            client.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
