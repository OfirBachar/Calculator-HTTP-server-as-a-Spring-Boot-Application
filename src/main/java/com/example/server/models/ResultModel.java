package com.example.server.models;

public class ResultModel {
    private int result;
    private String errorMessage;

    public ResultModel() {
    }

    public int getResult() {
        return result;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
