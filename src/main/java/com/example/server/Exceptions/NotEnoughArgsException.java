package com.example.server.Exceptions;

public class NotEnoughArgsException extends Exception {
    public NotEnoughArgsException(String message)
    {
        super(message);
    }
}
