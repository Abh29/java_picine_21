package edu.school21.services;

import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;


public class UsersServiceImplTest {

    @Mock
    private UsersRepository users;

    @InjectMocks
    private UsersServiceImpl usersService;

    @BeforeEach
    void init() {
        System.out.println("stubbing mock");
        users = Mockito.mock(UsersRepository.class);
        when(users.findByLogin("user1")).thenReturn(new User(1L, "user1", "secret", false));
        when(users.findByLogin("user2")).thenReturn(new User(2L, "user2", "secret", false));
        when(users.findByLogin("user3")).thenReturn(new User(3L, "user3", "secret", true));
        when(users.findByLogin("user4")).thenThrow(new EntityNotFoundException());

        usersService = new UsersServiceImpl(users);

        doAnswer(invocation -> {
            User user = invocation.getArgumentAt(0, User.class);
            if (user == null)
                return null;
            if (user.getId() >= 4 || user.getId() < 1)
                throw new EntityNotFoundException();
            if (user.getId() < 4){
                user.setAuthenticated(true);
                when(users.findByLogin(user.getLogin())).thenReturn(new User(user.getId(), user.getLogin(), user.getPassword(), true));
            }
            return user;
        }).when(users).update(any(User.class));

    }

    @Test
    void authenticateTest()  {
        User user1 = new User(1L, "user1", "secret", false);

        try{
            assertTrue(usersService.authenticate("user1", "secret"));
            assertFalse(usersService.authenticate("user2", "secretfalse"));
            assertThrows(AlreadyAuthenticatedException.class, () -> {
                usersService.authenticate("user3", "secret");
            });

            assertThrows(EntityNotFoundException.class, () -> {
                usersService.authenticate("users4", "secret");
            });

            assertThrows(AlreadyAuthenticatedException.class, () -> {
                usersService.authenticate("user1", "secret");
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
