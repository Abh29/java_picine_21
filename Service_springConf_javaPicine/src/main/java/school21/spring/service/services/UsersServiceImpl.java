package school21.spring.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;

import java.util.Optional;


@Component
public class UsersServiceImpl implements UsersService{

    private UsersRepository<User> repository;


    @Autowired
    public UsersServiceImpl(@Qualifier("usersRepositoryJdbcImpl") UsersRepository<User> repository) {
        this.repository = repository;
    }

    @Override
    public String signUp(String email) {
        String password = "secret";

        Optional<User> optionalUser = repository.findByEmail(email);

        if (optionalUser.isPresent()) {
            System.err.println("email already exist in the database");
            return null;
        }

        User user = new User((long) (repository.getRecordsCount() + 1), email, password);
        repository.save(user);
        return password;

    }
}
