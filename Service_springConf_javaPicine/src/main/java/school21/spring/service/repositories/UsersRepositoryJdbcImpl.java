package school21.spring.service.repositories;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UsersRepositoryJdbcImpl implements UsersRepository<User>{

    private final DataSource DATA_SOURCE;

    @Autowired
    public UsersRepositoryJdbcImpl(@Qualifier("hikariDataSource") DataSource dataSource) {
        this.DATA_SOURCE = dataSource;
    }

    @Override
    public User findById(Long id) {

        try{
            Connection connection = DATA_SOURCE.getConnection();
            String sql = "SELECT * from users where id=?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);

            ResultSet rs = statement.executeQuery();
            if (rs.next())
                return new User(rs.getLong("id"), rs.getString("email"), rs.getString("password"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> findAll() {

        List<User> out = new ArrayList<>();

        try{
            Connection connection = DATA_SOURCE.getConnection();
            String sql = "SELECT * from users;";

            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                out.add(new User(rs.getLong("id"), rs.getString("email"), rs.getString("password")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return out;
    }

    @Override
    public void save(User entity) {
        if (entity == null)
            return;
        try{
            Connection connection = DATA_SOURCE.getConnection();
            String sql = "INSERT INTO users(id, email, password) values(?, ?, ?);";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, entity.getID());
            statement.setString(2, entity.getEmail());
            statement.setString(3, entity.getPassword());

            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User entity) {
        if (entity == null)
            return;
        try{
            Connection connection = DATA_SOURCE.getConnection();
            String sql = "UPDATE users SET email=?, password=? WHERE id=?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(3, entity.getID());
            statement.setString(1, entity.getEmail());
            statement.setString(2, entity.getPassword());

            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void delete(Long id) {
        try{
            Connection connection = DATA_SOURCE.getConnection();
            String sql = "DELETE FROM users WHERE id=?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);

            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try{
            Connection connection = DATA_SOURCE.getConnection();
            String sql = "SELECT * from users where email=?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);

            ResultSet rs = statement.executeQuery();
            if (rs.next())
                return Optional.of (new User(rs.getLong("id"), rs.getString("email"), rs.getString("password")));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public int getRecordsCount() {
        try{
            Connection connection = DATA_SOURCE.getConnection();
            String sql = "SELECT count(*) from users";

            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet rs = statement.executeQuery();
            if (rs.next())
                return rs.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}