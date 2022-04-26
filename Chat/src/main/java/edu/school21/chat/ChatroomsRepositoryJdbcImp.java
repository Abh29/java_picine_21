package edu.school21.chat;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ChatroomsRepositoryJdbcImp implements ChatroomsRepository {

    private DataSource dataSource;
    private Connection connection;

    public ChatroomsRepositoryJdbcImp(DataSource dataSource) throws SQLException {
        this.dataSource = dataSource;
        this.connection = dataSource.getConnection();
    }

    @Override
    public Optional<Chatroom> findById(Long id) {
        String sql = "select * from chatrooms where id = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next())
            {
                User owner = new UsersRepositoryJdbcImp(dataSource)
                        .findById(rs.getLong(3))
                        .get();
                Chatroom chatroom = new Chatroom(rs.getLong(1),
                        rs.getString(2), owner);
                return Optional.of(chatroom);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
