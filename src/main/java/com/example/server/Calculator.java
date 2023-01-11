package com.example.server;

import com.example.server.Exceptions.DivisionException;
import com.example.server.Exceptions.NegativeException;

import java.util.List;

public class Calculator {
    public static int calculate(List<Integer> arguments ,String operation) throws DivisionException, NegativeException {
        int res = 0;
        int x = arguments.get(0);

        switch (operation.toUpperCase()) {
            case "PLUS" -> res = plus(x, arguments.get(1));
            case "MINUS" -> res = minus(x, arguments.get(1));
            case "TIMES" -> res = times(x, arguments.get(1));
            case "DIVIDE" -> {
                if(arguments.get(1) == 0)
                    throw new DivisionException("Error while performing operation Divide: division by 0");
                else
                    res = divide(x, arguments.get(1));
            }
            case "POW" -> res = pow(x, arguments.get(1));
            case "ABS" -> res = abs(x);
            case "FACT" ->{
                if (x < 0) {
                    throw new NegativeException("Error while performing operation Factorial: not supported for the negative number");
                }else
                    res = fact(x);
            }
        }
        return res;
    }


    public static int plus(int x, int y){
        return x+y;
    }

    public static int minus(int x, int y) {
        return x - y;
    }

    public static int times(int x, int y) {
        return x * y;
    }

    public static int divide(int x, int y) {
        return x / y;
    }

    public static int pow(int x, int y) {
        return (int) Math.pow(x, y);
    }

    public static int abs(int x) {
        return Math.abs(x);
    }

    public static int fact(int x) {
        int res = 1;
        for(int i = 1; i <= x; i++){
            res = res*i;
        }
        return res;
    }
}
