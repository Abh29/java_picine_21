package edu.school21.tanks.helpers;

import edu.school21.tanks.models.Player;
import edu.school21.tanks.models.User;
import edu.school21.tanks.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;


@Component
public class PlayersMapper implements RowMapper<Player> {

    private final UsersRepository<User> usersRepository;

    @Autowired
    public PlayersMapper(UsersRepository<User> usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public Player mapRow(ResultSet rs, int i) throws SQLException {

        Player player = new Player(
                rs.getLong("id"),
                usersRepository.findById(rs.getLong("user_id")).get(),
                rs.getString("nick"),
                rs.getDouble("hp"),
                new Vect2D(rs.getInt("position_x"), rs.getInt("position_x")),
                rs.getDouble("attack_power"),
                rs.getInt("shots")
        );
        return player;
    }
}
