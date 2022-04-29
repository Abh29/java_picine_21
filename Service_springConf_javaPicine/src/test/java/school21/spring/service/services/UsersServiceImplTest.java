package school21.spring.service.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import school21.spring.config.TestApplicationConfig;
import school21.spring.service.config.ApplicationConfig;
import school21.spring.service.repositories.UsersRepositoryJdbcImpl;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.*;

class UsersServiceImplTest {

    private UsersService usersService;

    @BeforeEach
    void setUp() {
        DataSource source = new TestApplicationConfig().dataSource();
        usersService = new UsersServiceImpl(new UsersRepositoryJdbcImpl(source));
        System.out.println("ok!");
    }

    @Test
    void signUp() {
        assertNull(usersService.signUp("user1@test.com"));
        assertEquals(usersService.signUp("newEmail@test.com"), "secret");

    }
}