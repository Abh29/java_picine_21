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
        }
        return Optional.empty();
    }

    @Override
    public Long saveMessage(Long authorId, Long roomId, String text) throws Exception{

        Optional<User> author = new UsersRepositoryJdbcImp(dataSource).findById(authorId);
        if (!author.isPresent())
            throw new MyExeptions.NotSavedSubEntityException("user id not found " + authorId);

        Optional<Chatroom> room = new ChatroomsRepositoryJdbcImp(dataSource).findById(roomId);
        if (!room.isPresent())
            throw new MyExeptions.NotSavedSubEntityException("user id not found " + authorId);

        String sql = "INSERT INTO messages (author,room,text,created_at) values (?,?,?,now()) returning id;";

        PreparedStatement statement = null;

        try{
            statement = connection.prepareStatement(sql);
            statement.setLong(1,authorId);
            statement.setLong(2,roomId);
            statement.setString(3,text);

            ResultSet rs = statement.executeQuery();
            if (rs.next())
                return Long.parseLong(rs.getString(1));

        }catch (SQLException e){
            e.printStackTrace();
        }
        return (long) -1;
    }

    @Override
    public boolean updateMessage(Message message){

        if (message == null || message.getId() == null)
            return false;

        String sql = "update messages set author = ?, room = ?, text = ?, created_at = ? where id = ?";

        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, message.getAuthor().getId());
            statement.setLong(2, message.getRoom().getId());
            statement.setString(3, message.getText());
            statement.setLong(5, message.getId());
            statement.setTimestamp(4, message.getTimestamp());
            statement.execute();
            return true;
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
