package ru.thesn.test_client.command;


import ru.thesn.test_client.Connector;
import ru.thesn.test_client.ConsoleHelper;
import ru.thesn.test_client.Start;
import ru.thesn.test_client.data.Marshaling;
import ru.thesn.test_client.data.Request;
import ru.thesn.test_client.data.Script;
import ru.thesn.test_client.exception.InterruptOperationException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

class MultiRequestCommand implements Command{

    @Override
    public void execute() throws InterruptOperationException {
        Connector connector = Connector.getInstance();
        while(true) {
            try {
                ConsoleHelper.writeMessage("Введите название сценария:");
                String xml = loadXMLFile("/scripts/" + ConsoleHelper.readString() + ".xml");
                Script script = (Script) Marshaling.unmarshal(xml, Script.class);
                for(Request request: script.getRequests()) {
                    String data = Marshaling.marshal(request);
                    ConsoleHelper.writeMessage("\nВаш запрос: \n" + data);
                    ConsoleHelper.writeMessage("\nОтвет сервера: \n" + connector.executeQuery(data));
                }
                break;
            }catch (FileNotFoundException e){
                ConsoleHelper.writeMessage("Файл не найден!");
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }


    private static String loadXMLFile(String path) throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        InputStream stream = Start.class.getResourceAsStream(path);
        if (stream == null) throw new FileNotFoundException();
        try(Scanner scanner = new Scanner(stream)) {
            scanner.useDelimiter("\r");
            while (scanner.hasNext())
                sb.append(scanner.next());
        }
        return sb.toString();
    }
}
