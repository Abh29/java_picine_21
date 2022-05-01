package edu.school21.tanks.repositories;

import edu.school21.tanks.helpers.GamesMapper;
import edu.school21.tanks.models.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class GamesRepositoryImpl implements GamesRepository<Game> {

    private final DataSource DATASOURCE;
    private JdbcTemplate template;
    private GamesMapper gamesMapper;

    @Autowired
    public GamesRepositoryImpl(DataSource DATASOURCE, GamesMapper gamesMapper) {
        this.DATASOURCE = DATASOURCE;
        template = new JdbcTemplate(DATASOURCE);
        this.gamesMapper = gamesMapper;
    }

    @Override
    public Optional<Game> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Game> findAll() {
        return null;
    }

    @Override
    public Long save(Game entity) {
        return 0L;
    }

    @Override
    public void update(Game entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Game> getCurrentGames() {
        return null;
    }
}
