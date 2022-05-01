package edu.school21.tanks.services;


import edu.school21.tanks.exceptions.*;
import edu.school21.tanks.models.Player;
import edu.school21.tanks.models.User;
import edu.school21.tanks.repositories.PlayersRepository;
import edu.school21.tanks.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService{

    private UsersRepository<User> usersRepository;
    private PlayersRepository<Player> playersRepository;
    private PasswordEncoder encoder;


    @Autowired
    public UsersServiceImpl(UsersRepository<User> usersRepository, PasswordEncoder encoder, PlayersRepository<Player> playersRepository) {
        this.usersRepository = usersRepository;
        this.playersRepository = playersRepository;
        this.encoder = encoder;
    }

    @Override
    public User signUp(String userName, String password) throws GameException {

        password = encryptPassword(password);
        Optional<User> optionalUser = usersRepository.findByUserName(userName);

        if (optionalUser.isPresent())
            throw new UserAlreadySignedUpException("this userName is already signed up!");

        User user = new User((long) (usersRepository.getRecordsCount() + 1), userName, password);
        usersRepository.save(user);
        return user;
    }

    @Override
    public User signIn(String userName, String password) throws GameException {
        password = encryptPassword(password);

        Optional<User> optionalUser = usersRepository.findByUserName(userName);
        if (!optionalUser.isPresent())
            throw new UsernameNotFoundException("this userName is not registered!");
        User user = optionalUser.get();

        if (!password.equals(user.getPassword()))
            throw new WrongPasswordExeption("wrong password was supplied!");

        return user;
    }

    @Override
    public Player registerNewPlayer(User user, String nick) throws GameException {

        Optional<Player> optionalPlayer = playersRepository.getPlayerByNick(nick);

        if(optionalPlayer.isPresent())
            throw new PlayerNickAlreadyTaken("this nick name is taken " + nick);

        Player player = new Player(user);

        player.setId(playersRepository.save(player));

        return player;
    }

    @Override
    public Player fetchPlayer(User user, String nick) throws GameException {

        Optional<Player> optionalPlayer = playersRepository.getPlayerByNick(nick);
        if (!optionalPlayer.isPresent())
            throw new NoNickNameWasFoundException("there is not record of this nickName " + nick);

        Player player = optionalPlayer.get();
        if (user.getId() != player.getPlayer().getId())
            throw new IllegalNickNameException("this nick name does not belong to user!");
        return player;
    }

    private String encryptPassword(String password){
        return encoder.encode(password);
    }

}
