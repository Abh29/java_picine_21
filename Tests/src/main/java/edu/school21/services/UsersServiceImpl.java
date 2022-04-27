package edu.school21.services;

import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;

import javax.persistence.EntityNotFoundException;

public class UsersServiceImpl {

    private final UsersRepository users;

    UsersServiceImpl(UsersRepository users){
        this.users = users;
    }

    boolean authenticate(String login, String password) throws Exception {

            User user = null;

            user = users.findByLogin(login);

            if (user == null)
                throw new EntityNotFoundException(login);

            if (user.isAuthenticated())
                throw new AlreadyAuthenticatedException(login);

            if(!user.getPassword().equals(password))
                return false;
            users.update(user);

            return true;
    }

}
