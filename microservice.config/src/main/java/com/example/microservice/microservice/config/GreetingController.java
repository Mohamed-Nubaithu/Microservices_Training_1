package com.example.microservice.microservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class GreetingController {

    @Value("${my.greetings: Hi default}")
    private String greetings;

    @Value("Direct Message")
    private String directMessage;

    @Value("${my.list.values: zero, one}")
    private List<String> myList;

    @Value("#{${db.connection: {key1:'value1', key2:'value2'}}}")
    private Map<String, String> map;

    @Autowired
    private DbSetting dbSetting;

    @GetMapping("/greetings")
    private String greetings()
    {
//        return greetings + " "+directMessage+ " "+myList+ " "+map;
        return dbSetting.getConnection()+ " "+dbSetting.getHost();
    }
}
