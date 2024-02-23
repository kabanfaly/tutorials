package com.kaba.service;

public class MyServiceImpl implements MyService {
    @Override
    public String sayHello(String s) {
        return "Hello World " + s;
    }
}
