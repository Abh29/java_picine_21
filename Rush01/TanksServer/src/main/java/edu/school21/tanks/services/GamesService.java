package edu.school21.tanks.services;

import edu.school21.tanks.exceptions.GameException;
import edu.school21.tanks.models.Game;
import edu.school21.tanks.models.Player;

public interface GamesService {

    Game startNewGame(Player player1, Player player2) throws GameException;
}
