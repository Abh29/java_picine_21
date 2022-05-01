package edu.school21.tanks.exceptions;

public class UserAlreadySignedUpException extends GameException {
    public UserAlreadySignedUpException(String msg) {
        super(msg);
    }
}
