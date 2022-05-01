package edu.school21.tanks.services;

import edu.school21.tanks.exceptions.GameException;
import edu.school21.tanks.exceptions.IllegalGameArgumentsException;
import edu.school21.tanks.models.Game;
import edu.school21.tanks.models.Player;
import edu.school21.tanks.repositories.GamesRepository;
import org.springframework.stereotype.Service;

@Service
public class GamesServiceImpl implements GamesService{

    GamesRepository<Game> gamesRepository;

    @Override
    public Game startNewGame(Player player1, Player player2) throws GameException {

        if (player1 == player2 || !player1.isAlive() || !player2.isAlive())
            throw new IllegalGameArgumentsException("game players should be different and alive");

        Game game = new Game(player1, player2, 100.0);

        game.setId(gamesRepository.save(game));

        return game;
    }
}
