package com.example.demo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @PostMapping("/printJson")
    public void printJson(@RequestBody String json) {

        System.out.println("JSON recebido: " + json);
    }
}
