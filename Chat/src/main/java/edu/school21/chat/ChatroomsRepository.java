package edu.school21.chat;

import java.util.Optional;

public interface ChatroomsRepository {

    public Optional<Chatroom> findById(Long id);
}
