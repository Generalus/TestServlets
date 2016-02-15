package ru.thesn.test_client.command;

import ru.thesn.test_client.exception.InterruptOperationException;

interface Command {
    void execute() throws InterruptOperationException;
}
