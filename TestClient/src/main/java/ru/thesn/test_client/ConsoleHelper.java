package ru.thesn.test_client;

import ru.thesn.test_client.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private ConsoleHelper(){}

    public static void writeMessage(String message){
        System.out.println(message);
    }

    public static void writeSmallMessage(String message){
        System.out.print(message);
    }

    public static String readString() throws InterruptOperationException
    {
        String s = "";
        try {
            s = reader.readLine();
        } catch(IOException e){
            writeMessage("Ошибка чтения строки!");
        }
        if (s.equalsIgnoreCase("exit"))
            throw new InterruptOperationException();
        return s;
    }

    public static double readDouble() throws InterruptOperationException {
        double result = 0;
        while(true) {
            try {
                String str = readString();
                result = Double.parseDouble(str);
                break;
            }catch (Exception e){
                writeMessage("Введено не число!");
            }
        }
        return result;
    }

    public static Operation askOperation() throws InterruptOperationException{
        while(true){
            try{
                return Operation.getAllowableOperationByOrdinal(Integer.parseInt(readString()));
            } catch(NumberFormatException e){
                writeMessage("Введено не число!");
            }
            catch(IllegalArgumentException e){
                writeMessage("Такой операции не существует!");
            }
        }
    }

    public static void printExitMessage() {
        writeMessage("Удачи!");
    }
}
