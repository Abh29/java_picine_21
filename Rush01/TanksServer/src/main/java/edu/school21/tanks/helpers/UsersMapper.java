package edu.school21.tanks.helpers;

import edu.school21.tanks.models.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UsersMapper implements RowMapper<User>{

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        return new User(resultSet.getLong("id"), resultSet.getString("user_name"), resultSet.getString("password"));
    }
}
