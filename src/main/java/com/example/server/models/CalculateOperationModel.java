package com.example.server.models;

import java.util.ArrayList;
import java.util.List;

public class CalculateOperationModel {
    List<Integer> arguments = new ArrayList<>();
    private String operation;

    public List<Integer> getArguments() {
        return arguments;
    }

    public String getOperation() {
        return operation;
    }
}
