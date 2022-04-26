package edu.school21.chat;

import java.util.Optional;

public interface MessagesRepository {

    public Optional<Message> findById(Long id);
    public Long saveMessage(Long authorId, Long roomId, String text) throws Exception;
}
