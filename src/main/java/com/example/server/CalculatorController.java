package com.example.server;

import com.example.server.Exceptions.*;
import com.example.server.models.CalculateOperationModel;
import com.example.server.models.OperationModel;
import com.example.server.models.ResultModel;
import com.example.server.models.StackArgsModel;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static com.example.server.Validations.*;

@RestController
public class CalculatorController {
    private static Logger stackLogger = LogManager.getLogger("stack-logger");
    private static Logger independentLogger = LogManager.getLogger("independent-logger");
    private final Stack<Integer> stack = new Stack<>();

    @Autowired
    private RequestsCounter requestCounter;

    @PostMapping("/independent/calculate")
    public ResultModel CalculatorResult(@RequestBody CalculateOperationModel calculator) throws TooMuchArgsException, NotEnoughArgsException, UnknownOperationException, DivisionException, NegativeException {
        checkValidOperation(calculator.getOperation(),independentLogger);
        checkTheNumberOfArgs(calculator.getArguments(), calculator.getOperation(),independentLogger);

        ResultModel resultModel = new ResultModel();
        resultModel.setResult(Calculator.calculate(calculator.getArguments(),calculator.getOperation()));

        independentLogger.info("Performing operation {}. Result is {}", calculator.getOperation(),resultModel.getResult());
        independentLogger.debug("Performing operation: {}({}) = {}", calculator.getOperation(),ListToString(calculator.getArguments()),resultModel.getResult());

        return resultModel;
    }

    @GetMapping("/stack/size")
    public ResultModel StackSize() {
        stackLogger.info("Stack size is {}", stack.size());
        StringBuilder sb = new StringBuilder(stack.toString().replace("[","").replace("]",""));
        stackLogger.debug("Stack content (first == top): [{}]",sb.reverse().toString());
        ResultModel resultModel = new ResultModel();
        resultModel.setResult(stack.size());
        return resultModel;
    }

    @PutMapping("/stack/arguments")
    public ResultModel AddArgsToStack(@RequestBody StackArgsModel stackArgsModel) {
        int prevStackSize = stack.size();
        for (int i = 0; i < stackArgsModel.getArgs().size(); i++) {
            stack.push(stackArgsModel.getArgs().get(i));
        }
        stackLogger.info("Adding total of {} argument(s) to the stack | Stack size: {}", stackArgsModel.getArgs().size(),stack.size());
        stackLogger.debug("Adding arguments: {} | Stack size before {} | stack size after {}", ListToString(stackArgsModel.getArgs()),prevStackSize, stack.size());
        ResultModel resultModel = new ResultModel();
        resultModel.setResult(stack.size());
        return resultModel;
    }

    @GetMapping("/stack/operate")
    public ResultModel PerformTheOperation(@RequestParam String operation) throws UnknownOperationException, DivisionException, NegativeException, NotEnoughArgsException {
        checkValidOperation(operation,stackLogger);
        checkTheNumberOfArgsInStack(stack.size(), operation,stackLogger);

        OperationModel operationModel = new OperationModel(operation);
        List<Integer> arguments = new ArrayList<>();
        for(int i = 0; i<operationModel.getNumOfArg(); i++){
            arguments.add(stack.pop());
        }

        ResultModel resultModel = new ResultModel();
        resultModel.setResult(Calculator.calculate(arguments,operation));

        stackLogger.info("Performing operation {}. Result is {} | stack size: {}", operation,resultModel.getResult(),stack.size());
        stackLogger.debug("Performing operation: {}({}) = {}", operation,ListToString(arguments),resultModel.getResult());

        return resultModel;
    }

    @DeleteMapping("/stack/arguments")
    public ResultModel StackRemoveArguments(@RequestParam int count) throws NotEnoughArgsException {
        if (count > stack.size()){
            String errorString = "Error: cannot remove " + count + " from the stack. It has only " + stack.size() + " arguments";
            stackLogger.error("Server encountered an error ! message: {}",errorString);
            throw new NotEnoughArgsException(errorString);
        }
        else{
            for (int i = 0; i < count; i++)
                stack.pop();
            stackLogger.info("Removing total {} argument(s) from the stack | Stack size: {}",count,stack.size());
            ResultModel resultModel = new ResultModel();
            resultModel.setResult(stack.size());
            return resultModel;
        }
    }

    @GetMapping("/logs/level")
    public String GetCurrentLoggerLevel(@RequestParam(name = "logger-name") String loggerName) {
        Logger logger = LogManager.getLogger(loggerName);
        if (logger.getName().equals("ROOT"))
            return "Failure to find the logger";
        return logger.getLevel().name().toUpperCase();
    }

    @PutMapping("/logs/level")
    public String GetCurrentLoggerLevel(@RequestParam(name = "logger-name") String loggerName, @RequestParam(name = "logger-level") String level) {
        Logger curLogger = LogManager.getLogger(loggerName);
        if (curLogger.getName().equals("ROOT"))
            return "Failure to find the logger";
        Configurator.setLevel(loggerName, Level.getLevel(level));
        return curLogger.getLevel().name().toUpperCase();
    }

}

