package edu.school21.chat;

import java.util.List;
import java.util.Optional;

public interface UsersRepository {
    public Optional<User> findById(long id);
    public List<User> findAll(int page, int size);
}
