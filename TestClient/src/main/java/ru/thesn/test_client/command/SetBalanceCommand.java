package ru.thesn.test_client.command;


import ru.thesn.test_client.Connector;
import ru.thesn.test_client.ConsoleHelper;
import ru.thesn.test_client.data.Marshaling;
import ru.thesn.test_client.data.Request;
import ru.thesn.test_client.exception.InterruptOperationException;

import java.io.IOException;
import java.net.ConnectException;

class SetBalanceCommand implements Command {

    @Override
    public void execute() throws InterruptOperationException {
        Connector connector = Connector.getInstance();
        ConsoleHelper.writeMessage("Введите логин:");
        String login = ConsoleHelper.readString();
        ConsoleHelper.writeMessage("Введите пароль:");
        String password = ConsoleHelper.readString();
        ConsoleHelper.writeMessage("Введите баланс:");
        double balance = ConsoleHelper.readDouble();

        Request request = new Request();
        request.setType("setBalance");
        request.setLogin(login);
        request.setPassword(password);
        request.setBalance(balance);

        String query = Marshaling.marshal(request);
        ConsoleHelper.writeMessage("Подготовлен запрос: \n" + query);
        try {
            ConsoleHelper.writeMessage("\nПолучен ответ: \n" + connector.executeQuery(query));
        } catch (ConnectException e){
            ConsoleHelper.writeMessage("Ошибка соединения с сервером!");
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
