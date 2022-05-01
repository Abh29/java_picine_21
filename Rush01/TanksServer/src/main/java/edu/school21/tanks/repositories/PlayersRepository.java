package edu.school21.tanks.repositories;

import edu.school21.tanks.models.Player;

import java.util.Optional;

public interface PlayersRepository<T extends Player> extends CrudRepository<T> {

    public Optional<T> getPlayerByNick(String nick);
}
