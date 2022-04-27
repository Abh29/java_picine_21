package edu.school21.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.*;

public class EmbeddedDataSourceTest {

    DataSource dataSource;

    @BeforeEach
    void init(){
        System.out.println("init");
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        dataSource = builder
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("/schema.sql")
                .addScript("/data.sql")
                .build();
    }

    @Test
    void setDataSourceTest(){
        assertNotNull(dataSource);
    }


}
