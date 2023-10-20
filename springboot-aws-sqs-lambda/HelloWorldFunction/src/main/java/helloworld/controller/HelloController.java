package helloworld.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HelloController {

    @GetMapping("/")
    public String hello() {
        log.info("Hello Hello");
        return "Hello World";
    }

    @PostMapping("/add")
    public String addHello(String msg) {
        log.info("Add Hello {}", msg);
        return "Hello " + msg;
    }
}
