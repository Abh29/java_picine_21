package edu.school21.tanks.repositories;

import edu.school21.tanks.models.Game;

import java.util.List;

public interface GamesRepository<T extends Game> extends CrudRepository<T> {

    public List<T> getCurrentGames();

}
