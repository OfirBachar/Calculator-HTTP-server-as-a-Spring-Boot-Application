package com.example.server;

import com.example.server.Exceptions.NotEnoughArgsException;
import com.example.server.Exceptions.TooMuchArgsException;
import com.example.server.Exceptions.UnknownOperationException;
import com.example.server.models.OperationModel;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Validations {

    public static void checkTheNumberOfArgsInStack(int sizeOfStack, String operation,Logger logger) throws NotEnoughArgsException {
        OperationModel operationModel = new OperationModel(operation);
        if (operationModel.getNumOfArg() > sizeOfStack){
            String errorString = "Error: cannot implement operation " + operation + ". It requires " + operationModel.getNumOfArg() + " arguments and the stack has only " + sizeOfStack + " arguments";
            logger.error("Server encountered an error ! message: {}",errorString);
            throw new NotEnoughArgsException(errorString);
        }
    }
    public static void checkValidOperation (String operation, Logger logger) throws UnknownOperationException {

        if (operation.equalsIgnoreCase("PLUS") || operation.equalsIgnoreCase("MINUS") || operation.equalsIgnoreCase("TIMES") || operation.equalsIgnoreCase("DIVIDE") || operation.equalsIgnoreCase("POW") || operation.equalsIgnoreCase("ABS") || operation.equalsIgnoreCase("FACT"))
            return;
        else {
            String errorString = "Error: unknown operation: " + operation;
            logger.error("Server encountered an error ! message: {}", errorString);
            throw new UnknownOperationException(errorString);
        }
    }
    public static void checkTheNumberOfArgs(List<Integer> arguments, String operation, Logger logger) throws TooMuchArgsException, NotEnoughArgsException {
        int numOfArgs = arguments.size();
        OperationModel operationModel = new OperationModel(operation);
        String errorString;

        if (operationModel.getNumOfArg() == numOfArgs)
            return;
        else if (operationModel.getNumOfArg() > numOfArgs){
            errorString = "Error: Not enough arguments to perform the operation "+operation;
            logger.error("Server encountered an error ! message: {}",errorString);
            throw new NotEnoughArgsException(errorString);
        }
        else{
            errorString = "Error: Too many arguments to perform the operation "+operation;
            logger.error("Server encountered an error ! message: {}",errorString);
            throw new TooMuchArgsException(errorString);
        }
    }
    public static String ListToString(List<Integer> list) {
        StringBuilder sb = new StringBuilder();
        for (Integer s : list) {
            sb.append(s).append(",");
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }
}
