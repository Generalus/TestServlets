package ru.thesn.test_client.data;


import javax.xml.bind.annotation.*;
import java.util.ArrayList;

@XmlRootElement(name = "my_script")
@XmlAccessorType(XmlAccessType.FIELD)
public class Script implements MarshalAble {

    @XmlElement(name = "request")
    private ArrayList<Request> requests;

    public ArrayList<Request> getRequests() {
        return requests;
    }

    public void setRequests(ArrayList<Request> requests) {
        this.requests = requests;
    }

    @Override
    public String toString() {
        return "Script{" +
                "requests=" + requests +
                '}';
    }
}
