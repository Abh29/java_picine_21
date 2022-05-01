package edu.school21.tanks.repositories;

import edu.school21.tanks.helpers.UsersMapper;
import edu.school21.tanks.models.User;
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
public class UsersRepositoryImpl implements UsersRepository<User>{

    private final DataSource DATASOURCE;
    private JdbcTemplate template;
    private UsersMapper usersMapper;

    @Autowired
    public UsersRepositoryImpl(DataSource DATASOURCE, UsersMapper usersMapper) {
        this.DATASOURCE = DATASOURCE;
        this.usersMapper = usersMapper;
        template = new JdbcTemplate(DATASOURCE);
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.of(template.query("SELECT * FROM users WHERE id=?", usersMapper, id).stream().findAny().orElse(null));
    }

    @Override
    public List<User> findAll() {
        return template.query("SELECT * FROM users", usersMapper);
    }

    @Override
    public Long save(User entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO users(user_name, password) values(?, ?)";

        template.update(con -> {
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, entity.getUserName());
            statement.setString(2, entity.getPassword());
            return statement;
        }, keyHolder);

        return keyHolder.getKeyAs(Long.class);
    }

    @Override
    public void update(User entity) {
        template.update("UPDATE users SET user_name=?, password=?, games_played=?, games_won=? WHERE id=?", entity.getUserName(), entity.getPassword(), entity.getGamesPlayed(), entity.getGamesWon(), entity.getId());
    }

    @Override
    public void delete(Long id) {
        template.update("DELETE FROM users WHERE id=?", id);
    }

    @Override
    public Optional<User> findByUserName(String userName) {
         return Optional.of((User) template.query("SELECT * FROM users WHERE user_name=?", usersMapper, userName));
    }

    @Override
    public int getRecordsCount() {
        return template.queryForRowSet("select count(*) from users").getInt(1);
    }

}