package edu.school21.tanks.helpers;

import edu.school21.tanks.models.Game;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class GamesMapper implements RowMapper<Game> {
    @Override
    public Game mapRow(ResultSet resultSet, int i) throws SQLException {
        return null;
    }
}
