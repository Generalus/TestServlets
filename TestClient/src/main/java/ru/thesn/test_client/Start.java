package ru.thesn.test_client;


import ru.thesn.test_client.command.CommandExecutor;
import ru.thesn.test_client.exception.InterruptOperationException;

public class Start {

    public static void main(String[] args){

        try {
            Operation operation;
            do {
                ConsoleHelper.writeMessage("\n Выберите операцию: \n" +
                        " - Регистрация нового пользователя: 1;\n" +
                        " - Получение баланса пользователя: 2;\n" +
                        " - Изменение баланса пользователя: 3;\n" +
                        " - Запустить скрипт с набором команд: 4;\n" +
                        " - Выход из программы: 5.");
                operation = ConsoleHelper.askOperation();
                CommandExecutor.execute(operation);
            }
            while (operation != Operation.EXIT);
        } catch(InterruptOperationException e){
            ConsoleHelper.printExitMessage();
        }
    }

}
