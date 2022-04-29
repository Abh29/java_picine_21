package school21.spring.service.repositories;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository<User>{

    private final DataSource DATASOURCE;
    private JdbcTemplate template;

    public UsersRepositoryJdbcTemplateImpl(DataSource datasource) {
        DATASOURCE = datasource;
        template = new JdbcTemplate(datasource);
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
        template.update("INSERT INTO users(id, email) values(?, ?)", entity.getID(), entity.getEmail());
    }

    @Override
    public void update(User entity) {
        template.update("UPDATE users SET email=? WHERE id=?", entity.getID(), entity.getEmail());
    }

    @Override
    public void delete(Long id) {
        template.update("DELETE FROM users WHERE id=?", id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
       return template.query("SELECT * FROM users WHERE email=?", new Object[]{email}, new UserMapper())
               .stream().findAny();
    }


    private static class UserMapper implements RowMapper<User>{
        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            return new User(resultSet.getLong("id"), resultSet.getString("email"));
        }
    }

}
