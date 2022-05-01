package edu.school21.tanks.repositories;

import edu.school21.tanks.helpers.PlayersMapper;
import edu.school21.tanks.models.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class PlayersRepositoryImpl implements PlayersRepository<Player> {

    private final DataSource DATASOURCE;
    private JdbcTemplate template;
    private PlayersMapper playersMapper;

    @Autowired
    public PlayersRepositoryImpl(DataSource DATASOURCE, PlayersMapper playersMapper) {
        this.DATASOURCE = DATASOURCE;
        template = new JdbcTemplate(DATASOURCE);
        this.playersMapper = playersMapper;
    }


    @Override
    public Optional<Player> findById(Long id) {
        return Optional.of((Player) template.query("SELECT * FROM players WHERE id=?", playersMapper, id).stream().findAny().orElse(null));
    }

    @Override
    public List<Player> findAll() {
        return template.query("SELECT * FROM players", playersMapper);
    }

    @Override
    public Long save(Player entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO players(user_id, nick, hp, position_x, position_x, attack_power, shots) values(?, ?, ?, ?, ?, ?, ?)";

        template.update(con -> {
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setLong(1, entity.getPlayer().getId());
            statement.setString(2, entity.getNick());
            statement.setDouble(3, entity.getHp());
            statement.setInt(4, (int) entity.getPosition().getX());
            statement.setInt(5, (int) entity.getPosition().getY());
            statement.setDouble(6, entity.getAttackPower());
            statement.setInt(7, entity.getShots());
            return statement;
        }, keyHolder);

        return keyHolder.getKeyAs(Long.class);
    }

    @Override
    public void update(Player entity) {
        template.update("UPDATE players SET user_id=?, nick=?, hp=?, position_x=?, position_y=?, attack_power=?, shots=? WHERE id=?",
                entity.getPlayer().getId(),
                entity.getNick(),
                entity.getHp(),
                entity.getPosition().getX(),
                entity.getPosition().getY(),
                entity.getAttackPower(),
                entity.getShots(),
                entity.getId());
    }

    @Override
    public void delete(Long id) {
        template.update("DELETE FROM players WHERE id=?", id);
    }

    @Override
    public Optional<Player> getPlayerByNick(String nick) {
        return Optional.of(template.query("SELECT * FROM players WHERE nick=?", playersMapper, nick).stream().findAny().orElse(null));
    }
}
