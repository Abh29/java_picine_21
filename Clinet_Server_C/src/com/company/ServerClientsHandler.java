package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerClientsHandler extends Thread{
    private final Socket clientSocket;
    private PrintWriter clientOut;
    private BufferedReader clientIn;
    private boolean isRunning;

    ServerClientsHandler(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        clientOut = new PrintWriter(clientSocket.getOutputStream(), true);
        clientIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        isRunning = true;
    }

    @Override
    public void run() {
        String msg;
        int     count = 0;

        try
        {
            while (isRunning)
            {
                while (!clientIn.ready() && isRunning)
                {
                    Thread.sleep(10);
                    count++;
                    if (count >= 30000) // 5 min without connection
                        break;
                }
                if (!isRunning)
                    break;
                count = 0;
                msg = clientIn.readLine();
                clientOut.println("msg received.");
                System.out.println(msg);
            }
        }
        catch (Exception e)
        {
            System.out.println("connection closed !");
            isRunning = false;
        }
        finally {
            closeConnection();
        }
    }

    private void closeConnection()
    {
        clientOut.println("Server out.");
        try
        {
            clientOut.close();
            clientIn.close();
            clientSocket.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void interrupt() {
        isRunning = false;
        closeConnection();
        super.interrupt();
    }
}
