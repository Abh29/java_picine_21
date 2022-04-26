package edu.school21.chat;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Optional;

public class MessagesRepositoryJdbcImp implements MessagesRepository{

    private DataSource dataSource;
    private Connection connection;

    public MessagesRepositoryJdbcImp(DataSource dataSource) throws SQLException {
        this.dataSource = dataSource;
        this.connection = dataSource.getConnection();
    }

    @Override
    public Optional<Message> findById(Long id) {
        String sql = "select * from messages where id = ?;";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1,id);
            ResultSet rs = statement.executeQuery();
            if (rs.next())
            {
                User author = new UsersRepositoryJdbcImp(dataSource)
                        .findById(rs.getLong(2))
                        .get();
                Chatroom room = new ChatroomsRepositoryJdbcImp(dataSource)
                        .findById(rs.getLong(3))
                        .get();
                Message message = new Message(rs.getLong(1),
                        author, room, rs.getString(4),
                        Timestamp.valueOf(rs.getString(5)));
                return Optional.of(message);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }

        return Optional.empty();

    }
}
