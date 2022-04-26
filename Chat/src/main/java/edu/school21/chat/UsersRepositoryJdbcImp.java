package edu.school21.chat;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UsersRepositoryJdbcImp implements UsersRepository{

    private DataSource dataSource;
    private Connection connection;

    public UsersRepositoryJdbcImp(DataSource dataSource) throws SQLException {
        this.dataSource = dataSource;
        this.connection = dataSource.getConnection();
    }

    @Override
    public Optional<User> findById(long id) {

        String sql = "select * from chatters where id = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next())
            {
                User user = new User(rs.getLong(1),
                        rs.getString(2), rs.getString(3));
                return Optional.of(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
