package edu.school21.sockets.services;


import edu.school21.sockets.models.User;

public interface UsersService {

    User signUp(String userName, String password);
}