package ru.thesn.test_server.servlet;


import ru.thesn.test_server.data.MarshalAble;
import ru.thesn.test_server.data.Request;
import ru.thesn.test_server.data.Response;
import ru.thesn.test_server.jpa.JPAManager;
import ru.thesn.test_server.jpa.dao.UserDao;
import ru.thesn.test_server.jpa.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.security.MessageDigest;


public class TestPage extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        response.getWriter().println("answer");
        response.setContentType("text/xml;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        */
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InputStream stream = request.getInputStream();
        byte[] b = new byte[stream.available()];
        stream.read(b);
        Request data = (Request)unmarshal(new String(b), Request.class);
        Response answer = new Response();
        answer.setCode(0);

        JPAManager manager = JPAManager.getInstance();
        UserDao dao = manager.getUserDao();

        try {
            String login = data.getLogin();
            String password = data.getPassword();
            Double value = data.getBalance();

            switch (data.getType()) {
                case "registerCustomer":
                    if (dao.getUser(login) != null) {
                        answer.setCode(1);
                        break;
                    }
                    if (password == null) {
                        answer.setCode(3);
                        break;
                    }
                    User user = new User();
                    user.setLogin(login);
                    user.setPasswordHash(getHash(password));
                    dao.save(user);

                    break;

                case "setBalance":
                    User user1 = dao.getUser(login);
                    if (user1 == null) {
                        answer.setCode(1);
                        break;
                    }
                    if (!getHash(password).equals(user1.getPasswordHash())) {
                        answer.setCode(3);
                        break;
                    }
                    user1.setValue(value == null ? 0 : value);
                    dao.save(user1);
                    answer.setBalance(user1.getValue());
                    break;

                case "getBalance":
                    User user2 = dao.getUser(login);
                    if (user2 == null) {
                        answer.setCode(1);
                        break;
                    }
                    if (!getHash(password).equals(user2.getPasswordHash())) {
                        answer.setCode(3);
                        break;
                    }
                    answer.setBalance(user2.getValue());
                    break;

                default:
                    System.out.println("Сервер получил некорректный запрос!");
                    answer.setCode(4);
            }
        }catch (Exception e){
            e.printStackTrace();
            answer.setCode(4);
        }

        dao.close();
        //TODO manager.close(); должен вызываться один раз при завершении работы сервера

        // преобразовываем все записанное в StringWriter в строку
        response.setContentType("text/xml;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(marshal(answer));
    }

    private static String marshal(MarshalAble marshalAble){
        StringWriter writer = new StringWriter();
        try {
            // создание объекта Marshaller, который выполняет сериализацию
            JAXBContext context = JAXBContext.newInstance(marshalAble.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(marshalAble, writer);
        } catch (Exception e){
            e.printStackTrace();
        }
        return writer.toString();
    }


    private static MarshalAble unmarshal(String xml, Class clazz){
        MarshalAble object = null;
        try {
            StringReader reader = new StringReader(xml);
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            object = (MarshalAble) unmarshaller.unmarshal(reader);
        } catch (Exception e){
            e.printStackTrace();
        }
        return object;
    }

    public String getHash(String str) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(str.getBytes());

        byte[] dataBytes = md.digest();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dataBytes.length; i++) {
            sb.append(Integer.toString((dataBytes[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }

}

