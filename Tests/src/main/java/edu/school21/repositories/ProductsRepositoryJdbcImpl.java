package edu.school21.repositories;

import edu.school21.models.Product;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements ProductsRepository{

    private DataSource dataSource;
    private Connection connection;

    public ProductsRepositoryJdbcImpl(DataSource dataSource) throws SQLException {
        this.dataSource = dataSource;
        connection = dataSource.getConnection();
    }

    @Override
    public List<Product> findAll() {
        List<Product> out = new ArrayList<>();

        String sql = "select * from products";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next())
            {
                Product product = new Product(rs.getLong(1),
                        rs.getString(2), rs.getDouble(3));
                out.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return out;
    }

    @Override
    public Optional<Product> findById(Long id) {
        String sql = "select * from products where id = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next())
            {
                Product product = new Product(rs.getLong(1),
                        rs.getString(2), rs.getDouble(3));
                return Optional.of(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void update(Product product) {
        String sql = "update products set name = ?, price = ? where id = ?;";

        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(3, product.getId());
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());

            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return;
    }

    @Override
    public void save(Product product) {
        String sql = "insert into products values (?, ?, ?);";

        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, product.getId());
            statement.setString(2, product.getName());
            statement.setDouble(3, product.getPrice());

            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return;
    }

    @Override
    public void delete(Long id) {
        String sql = "delete from products where id = ?;";

        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);

            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return;
    }
}
