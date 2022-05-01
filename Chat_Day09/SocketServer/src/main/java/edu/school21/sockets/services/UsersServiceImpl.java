package edu.school21.sockets.services;


import edu.school21.sockets.models.User;
import edu.school21.sockets.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsersServiceImpl implements UsersService{

    private UsersRepository<User> repository;

    private PasswordEncoder encoder;


    @Autowired
    public UsersServiceImpl(@Qualifier("usersRepositoryJdbcImpl") UsersRepository<User> repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @Override
    public User signUp(String userName, String password) {

        password = encryptPassword(password);
        Optional<User> optionalUser = repository.findByUserName(userName);

        if (optionalUser.isPresent()) {
            System.err.println("email already exist in the database");
            return null;
        }

        User user = new User((long) (repository.getRecordsCount() + 1), userName, password);
        repository.save(user);
        return user;
    }

    private String encryptPassword(String password){
        return encoder.encode(password);
    }

}
