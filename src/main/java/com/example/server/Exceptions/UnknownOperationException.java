package com.example.server.Exceptions;

public class UnknownOperationException extends Exception {
    public UnknownOperationException(String message)
    {
        super(message);
    }
}
