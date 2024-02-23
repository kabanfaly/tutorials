package com.kaba.handler;

import com.kaba.ApplicationConfig;
import com.kaba.service.MyService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationHandler {
    public String handleRequest() {
        AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        MyService myService = configApplicationContext.getBean(MyService.class);
        return myService.sayHello("Kaba");
    }
}
