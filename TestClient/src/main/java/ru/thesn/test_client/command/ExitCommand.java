package ru.thesn.test_client.command;


import ru.thesn.test_client.ConsoleHelper;
import ru.thesn.test_client.exception.InterruptOperationException;

class ExitCommand implements Command {
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.printExitMessage();
    }
}
