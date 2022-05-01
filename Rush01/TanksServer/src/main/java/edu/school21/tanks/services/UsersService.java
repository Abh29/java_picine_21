package edu.school21.tanks.services;


import edu.school21.tanks.exceptions.GameException;
import edu.school21.tanks.models.Player;
import edu.school21.tanks.models.User;

public interface UsersService {

    User signUp(String userName, String password) throws GameException;

    User signIn(String userName, String password) throws GameException;

    Player registerNewPlayer(User user, String nick) throws GameException;

    Player fetchPlayer(User user, String nick) throws GameException;

}