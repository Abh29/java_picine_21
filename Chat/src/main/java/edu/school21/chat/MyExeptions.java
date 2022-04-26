package edu.school21.chat;

public class MyExeptions extends RuntimeException {

    MyExeptions(String msg){
        super(msg);
    }

    public static class NotSavedSubEntityException extends MyExeptions{
        NotSavedSubEntityException(String msg){
            super(msg);
        }
    }

}
