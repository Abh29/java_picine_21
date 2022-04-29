package school21.spring.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import school21.spring.service.config.ApplicationConfig;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;


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

        User user = repository.findByEmail(email).get();

        if (user != null) {
            System.err.println("email already exist in the database");
            return null;
        }
        user = new User((long) (repository.getRecordsCount() + 1), email, password);
        repository.save(user);
        return password;

    }
}
