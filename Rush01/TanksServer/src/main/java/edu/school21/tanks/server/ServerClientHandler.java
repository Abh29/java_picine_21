package edu.school21.tanks.server;

import edu.school21.tanks.exceptions.GameException;
import edu.school21.tanks.models.Player;
import edu.school21.tanks.models.User;
import edu.school21.tanks.services.UsersService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerClientHandler extends Thread{

    private final Socket clientSocket;
    private UsersService usersService;
    private PrintWriter clientOut;
    private BufferedReader clientIn;
    private volatile Player player;
    private boolean isAlive;


    public ServerClientHandler(Socket clientSocket, Player player) throws IOException {
        this.clientSocket = clientSocket;
        clientOut = new PrintWriter(clientSocket.getOutputStream());
        clientIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        isAlive = true;
        this.player = player;
    }

    @Override
    public void run() {
       listenToIdentifyRequest();
       User user = identifyUser();
       identifyPlayer(user);
        System.out.println("client done!");
    }

    private void listenToIdentifyRequest() {
        String msg;

        try{
            while (true){

                msg = clientIn.readLine();
                if (msg.equals("identify_me_request")){
                    clientOut.println("identify_me_response");
                    return;
                }
            }
        }catch (IOException e){
            e.printStackTrace();
            interrupt();
        }
    }

    private User identifyUser(){

        String msg = "";
        User user = null;
        boolean identified = false;

        try{
            while (!identified){
                msg = clientIn.readLine();

                if(msg.equals("signup_request")){
                    clientOut.println("signup_response");
                    identified = signUpClient(new User[] {user});
                }else if (msg.equals("signin_request")){
                    clientOut.println("signin_response");
                    identified = signInClient(new User[] {user});
                }else if (msg.equals("exit_request"))
                    throw new IOException();
            }
        } catch (IOException e) {
            e.printStackTrace();
            interrupt();
        }
        clientOut.println("identify_me_success");
        return user;
    }

    private boolean signInClient(User[] users) {

        String msg;
        String signInForm = "user_name=.+;password=.+;";


        try{
            msg = clientIn.readLine();

            if (msg.matches(signInForm)){
                String[] inputs = msg.split(";");

                String userName = inputs[0].substring(inputs[0].indexOf("=") + 1);
                String password = inputs[1].substring(inputs[0].indexOf("=") + 1);

                users[0] = usersService.signIn(userName, password);
            }


        }catch (IOException e){
            e.printStackTrace();
            clientOut.println("@err: something went wrong, try again!");
            return false;
        } catch (GameException e) {
            clientOut.println(e.getMessage());
            return false;
        }

        return true;
    }

    private boolean signUpClient(User[] users) {
        String msg;
        String signInForm = "user_name=.+;password=.+;";


        try{
            msg = clientIn.readLine();

            if (msg.matches(signInForm)){
                String[] inputs = msg.split(";");

                String userName = inputs[0].substring(inputs[0].indexOf("=") + 1);
                String password = inputs[1].substring(inputs[0].indexOf("=") + 1);

                users[0] = usersService.signUp(userName, password);
            }

        }catch (IOException e){
            e.printStackTrace();
            clientOut.println("@err: something went wrong, try again!");
            return false;
        } catch (GameException e) {
            clientOut.println(e.getMessage());
            return false;
        }

        return true;
    }

    private void identifyPlayer(User user) {
        String msg = "";
        boolean identified = false;

        try{
            while (!identified){
                msg = clientIn.readLine();

                if(msg.equals("create_player_request")){
                    clientOut.println("clreate_player_response");
                    identified = createPlayer(user);
                }else if (msg.equals("fetch_player_request")){
                    clientOut.println("fetch_player_response");
                    identified = fetchPlayer(user);
                }else if (msg.equals("exit_request"))
                    throw new IOException();
            }
        } catch (IOException e) {
            e.printStackTrace();
            interrupt();
        }
        clientOut.println("identify_player_success");
    }

    private boolean fetchPlayer(User user) {
        String msg;
        String fetchForm = "nick=.+;";

        try{
            msg = clientIn.readLine();

            if (msg.matches(fetchForm)){
                String[] inputs = msg.split(";");

                String nick = inputs[0].substring(inputs[0].indexOf("=") + 1);

                player = usersService.fetchPlayer(user, nick);

            }else
                throw new GameException("wrong Form received try again!");

        }catch (IOException e){
            e.printStackTrace();
            clientOut.println("@err: something went wrong, try again!");
            return false;
        } catch (GameException e) {
            clientOut.println(e.getMessage());
            return false;
        }

        return true;
    }

    private boolean createPlayer(User user) {
        String msg;
        String createForm = "nick=.+;";


        try{
            msg = clientIn.readLine();

            if (msg.matches(createForm)){
                String[] inputs = msg.split(";");

                String nick = inputs[0].substring(inputs[0].indexOf("=") + 1);

                player = usersService.registerNewPlayer(user, nick);

            }else
                throw new GameException("wrong Form received try again!");

        }catch (IOException e){
            e.printStackTrace();
            clientOut.println("@err: something went wrong, try again!");
            return false;
        } catch (GameException e) {
            clientOut.println(e.getMessage());
            return false;
        }

        return true;
    }

    public void setUsersService(UsersService usersService) {
        this.usersService = usersService;
    }


    private void closeConnection() {
        clientOut.println("Client out.");
        try
        {
            clientOut.close();
            clientIn.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void interrupt() {
        isAlive = false;
        closeConnection();
        super.interrupt();
    }

}
