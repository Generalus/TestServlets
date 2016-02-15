package ru.thesn.test_client.data;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class Marshaling {

    private Marshaling(){

    }

    public static String marshal(MarshalAble marshalAble){
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

    public static MarshalAble unmarshal(String xml, Class clazz){
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
}
