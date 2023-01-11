package com.example.server.models;

public class ErrorResultModel {
    private String errorMessage;

    public ErrorResultModel(String Message) {
        errorMessage=Message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
