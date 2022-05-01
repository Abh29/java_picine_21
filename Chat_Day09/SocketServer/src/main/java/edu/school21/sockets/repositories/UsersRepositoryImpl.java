package edu.school21.sockets.repositories;

import edu.school21.sockets.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryImpl implements UsersRepository<User>{

    private final DataSource DATASOURCE;

    private JdbcTemplate template;

    @Autowired
    public UsersRepositoryImpl(@Qualifier("hikariDataSource") DataSource DATASOURCE) {
        this.DATASOURCE = DATASOURCE;
        template = new JdbcTemplate(DATASOURCE);
    }

    @Override
    public User findById(Long id) {
        return template.query("SELECT * FROM users WHERE id=?", new Object[]{id}, new UserMapper())
                .stream().findAny().orElse(null);
    }

    @Override
    public List<User> findAll() {
        return template.query("SELECT * FROM users", new UserMapper());
    }

    @Override
    public void save(User entity) {
        template.update("INSERT INTO users(id, user_name, password) values(?, ?, ?)", entity.getID(), entity.getUserName(), entity.getPassword());
    }

    @Override
    public void update(User entity) {
        template.update("UPDATE users SET user_name=?, password=? WHERE id=?", entity.getUserName(), entity.getPassword(), entity.getID());
    }

    @Override
    public void delete(Long id) {
        template.update("DELETE FROM users WHERE id=?", id);
    }

    @Override
    public Optional<User> findByUserName(String userName) {
        return template.query("SELECT * FROM users WHERE user_name=?", new Object[]{userName}, new UserMapper())
                .stream().findAny();
    }

    @Override
    public int getRecordsCount() {
        return template.queryForRowSet("select count(*) from users").getInt(1);
    }

    private static class UserMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            return new User(resultSet.getLong("id"), resultSet.getString("user_name"), resultSet.getString("password"));
        }
    }
}