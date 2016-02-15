package ru.thesn.test_client.command;


import ru.thesn.test_client.Operation;
import ru.thesn.test_client.exception.InterruptOperationException;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutor {
    private static Map<Operation, Command> map = new HashMap<>();

    static {
        map.put(Operation.REGISTER_CUSTOMER, new RegisterCustomerCommand());
        map.put(Operation.GET_BALANCE, new GetBalanceCommand());
        map.put(Operation.SET_BALANCE, new SetBalanceCommand());
        map.put(Operation.MULTI_REQUEST, new MultiRequestCommand());
        map.put(Operation.EXIT, new ExitCommand());
    }

    private CommandExecutor(){}

    public static void execute(Operation operation) throws InterruptOperationException {
        map.get(operation).execute();
    }

}
