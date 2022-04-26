package edu.school21.chat;

import java.sql.Timestamp;
import java.util.Objects;

public class Message {
    private Long id;
    private User author;
    private Chatroom room;
    private String text;
    private Timestamp timestamp;


    public Message(Long id, User author, Chatroom room, String text, Timestamp timestamp) {
        this.id = id;
        this.author = author;
        this.room = room;
        this.text = text;
        this.timestamp = timestamp;
    }

    public Message(User author, Chatroom room, String text, Timestamp timestamp) {
        this.author = author;
        this.room = room;
        this.text = text;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Chatroom getRoom() {
        return room;
    }

    public void setRoom(Chatroom room) {
        this.room = room;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", \nauthor=" + author +
                ", \nroom=" + room +
                ", \ntext='" + text + '\'' +
                ", \ntimestamp=" + timestamp +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(id, message.id) && author.equals(message.author) && room.equals(message.room) && text.equals(message.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, room, text);
    }
}
