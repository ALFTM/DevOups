package com.esgi.devops.reactive.webapp;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Setter
@Getter
public class ReactiveStorage {

    private String origin;
    private String reactive;
    private String actual;

    public static String calculate(String input) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        String now = format.format(new Date());
        return input + " - " + now;
    }
}
