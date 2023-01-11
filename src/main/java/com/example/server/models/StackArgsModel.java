package com.example.server.models;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class StackArgsModel {
    final List<Integer> arguments;

    public StackArgsModel() {
        arguments = new ArrayList<>();
    }

    public List<Integer> getArgs() {
        return arguments;
    }


}
