package ru.thesn.test_client;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class Connector {

    public final static String PATH = "http://localhost:8080/test";
    public final static String CHARSET = "UTF-8";

    private static Connector instance;

    public static Connector getInstance() {
        if (instance == null)
            instance = new Connector();
        return instance;
    }

    private Connector(){
    }

    public String executeQuery(String query) throws IOException{
        URLConnection con = new URL(PATH).openConnection();
        con.setUseCaches(false);
        con.setDoOutput(true); // Triggers POST.
        con.setRequestProperty("accept-charset", CHARSET);
        con.setRequestProperty("content-type", "application/x-www-form-urlencoded");

        try (OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream(), CHARSET)) {
            writer.write(query); // Write POST query string (if any needed).
        }

        InputStream result = con.getInputStream();
        byte[] b = new byte[result.available()];
        result.read(b);
        return new String(b);
    }

}
