package com.jcisneros.contactcrud.bean;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import javax.annotation.PostConstruct;
import java.io.Serializable;

@Component("helloBean")
@SessionScope
public class HelloBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String message;

    @PostConstruct
    public void init() {
        message = "Hello, JSF with Spring Boot!";
    }

    // Getter and Setter
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
