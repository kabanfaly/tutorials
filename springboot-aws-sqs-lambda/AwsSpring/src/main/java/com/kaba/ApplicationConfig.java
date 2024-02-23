package com.kaba;

import com.kaba.service.MyService;
import com.kaba.service.MyServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Configuration
@ComponentScan("com.kaba")
public class ApplicationConfig {

    @Bean
    public MyService myService() {
        return new MyServiceImpl();
    }
}
