package com.esgi.devops.reactive.webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class RootController {

    @Autowired
    private ReactiveStorage storage;

    @GetMapping
    public ReactiveStorage getStorage() {
        storage.setActual(ReactiveStorage.calculate(storage.getOrigin()));

        return storage;
    }
}
