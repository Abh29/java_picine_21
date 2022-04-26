package edu.school21.chat;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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

    @Override
    public List<User> findAll(int page, int size) {
        int start = page * size;
        List<User> out = new ArrayList<>();

        String sql = "select id, login as user_login, password as user_password, room_id, room_name, owner_id, owner_login, owner_password\n" +
                "from\n" +
                "(\n" +
                "    select * from chatters\n" +
                "    order by id offset ? rows\n" +
                "    fetch next ? rows only\n" +
                ") page\n" +
                "join\n" +
                "(\n" +
                "    select tmp2.id as room_id, tmp2.name as room_name, tmp2.name, tmp2.owner_id, tmpc.login as owner_login, tmpc.password as owner_password, tmp2.chatter_id\n" +
                "    from\n" +
                "    (\n" +
                "        select chatrooms.id, chatrooms.name, owner_id, cct.chatter_id\n" +
                "        from chatrooms\n" +
                "        join chatter_chatroom_table cct on chatrooms.id = cct.chatroom_id\n" +
                "    ) tmp2\n" +
                "    join chatters tmpc on tmp2.owner_id = tmpc.id\n" +
                ")tmp\n" +
                "on page.id = tmp.chatter_id;";

        PreparedStatement statement = null;

        try{
            statement = connection.prepareStatement(sql);
            statement.setInt(1, start);
            statement.setInt(2, size);

            ResultSet rs = statement.executeQuery();
            while (rs.next()){

                User owner = new User(
                        rs.getLong("owner_id"),
                        rs.getString("owner_login"),
                        rs.getString("owner_password"),
                        new ArrayList<>(),
                        new ArrayList<>());

                if (out.contains(owner))
                    owner = out.get(out.indexOf(owner));

                Chatroom room = new Chatroom(
                        rs.getLong("room_id"),
                        rs.getString("room_name"),
                        owner);

                if (!owner.getCreatedRooms().contains(room))
                    owner.getCreatedRooms().add(room);

                User user = new User(
                        rs.getLong("id"),
                        rs.getString("user_login"),
                        rs.getString("user_password"),
                        new ArrayList<>(),
                        new ArrayList<>()
                );

                if (out.contains(user))
                    user = out.get(out.indexOf(user));

                user.getChatrooms().add(room);

                if (!out.contains(user))
                    out.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

        return out;
    }
}
