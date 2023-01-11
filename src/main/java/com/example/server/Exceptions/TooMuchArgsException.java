package com.example.server.Exceptions;

public class TooMuchArgsException extends Exception {
    public TooMuchArgsException(String message)
    {
        super(message);
    }
}
