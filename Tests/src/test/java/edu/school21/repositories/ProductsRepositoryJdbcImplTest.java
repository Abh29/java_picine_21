package edu.school21.repositories;

import edu.school21.models.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ProductsRepositoryJdbcImplTest {

    final List<Product> EXPECTED_FIND_ALL_PRODUCTS = new ArrayList<>();
    final Product EXPECTED_FIND_BY_ID_PRODUCT = new Product(2L, "product2", 150.29);
    final Product EXPECTED_UPDATED_PRODUCT = new Product(2L, "updated2", 139.99);

    DataSource dataSource;

    ProductsRepositoryJdbcImpl products;


    @BeforeEach
    void setUp() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        dataSource = builder
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("/schema.sql")
                .addScript("/data.sql")
                .build();
        try {
            products = new ProductsRepositoryJdbcImpl(dataSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    void initAllList(){
        EXPECTED_FIND_ALL_PRODUCTS.add(new Product(1L, "product1", 100.0));
        EXPECTED_FIND_ALL_PRODUCTS.add(new Product(2L, "product2", 150.29));
        EXPECTED_FIND_ALL_PRODUCTS.add(new Product(3L, "product3", 99.99));
        EXPECTED_FIND_ALL_PRODUCTS.add(new Product(4L, "product4", 37.36));
        EXPECTED_FIND_ALL_PRODUCTS.add(new Product(5L, "product5", 1230.23));
    }

    @Test
    void findAll() {
        initAllList();
        List<Product> actual = products.findAll();

        assertEquals(actual.size(), EXPECTED_FIND_ALL_PRODUCTS.size());
        assertEquals(actual, EXPECTED_FIND_ALL_PRODUCTS);
    }

    @Test
    void findById() {
        Optional<Product> actual = products.findById(2L);
        Optional<Product> actual2 = products.findById(10L);

        assertTrue(actual.isPresent());
        assertFalse(actual2.isPresent());
        assertEquals(actual.get(), EXPECTED_FIND_BY_ID_PRODUCT);
    }

    @Test
    void update() {
        products.update(EXPECTED_UPDATED_PRODUCT);

        Optional<Product> actual = products.findById(2L);
        assertTrue(actual.isPresent());
        assertEquals(actual.get(), EXPECTED_UPDATED_PRODUCT);
    }

    @Test
    void save() {
        Product newProduct = new Product(6L, "new product", 3.50);

        products.save(newProduct);

        Optional<Product> actual = products.findById(6L);
        assertTrue(actual.isPresent());
        assertEquals(actual.get(), newProduct);
    }

    @Test
    void delete() {
        products.delete(6L);
        Optional<Product> actual = products.findById(6L);
        assertFalse(actual.isPresent());
    }
}