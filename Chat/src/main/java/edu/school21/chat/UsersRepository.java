package edu.school21.chat;

import java.util.Optional;

public interface UsersRepository {
    public Optional<User> findById(long id);
}
