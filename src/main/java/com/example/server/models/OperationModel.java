package com.example.server.models;

public class OperationModel {
    private String type;
    private int numOfArgs;

    public OperationModel(String type) {
        this.type = type;

        switch (type.toUpperCase()) {
            case "PLUS", "MINUS", "TIMES", "DIVIDE", "POW" -> this.numOfArgs = 2;
            case "ABS", "FACT" -> this.numOfArgs = 1;
        }
    }
    public int getNumOfArg() {
        return numOfArgs;
    }

}
