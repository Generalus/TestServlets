package ru.thesn.test_client.data;

import javax.xml.bind.annotation.*;


@XmlRootElement(name = "request")
@XmlAccessorType(XmlAccessType.FIELD)
public class Request implements MarshalAble {
    private String type;
    private String login;
    private String password;
    private Double balance;

    public Request(){

    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String toString() {
        return "Request{" +
                "type='" + type + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                '}';
    }
}
