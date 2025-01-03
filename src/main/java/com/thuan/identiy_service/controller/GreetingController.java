package com.thuan.identiy_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequestMapping(value = "/greeting")
public class GreetingController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping()
    public String greet(Locale locale) {
        return messageSource.getMessage("greeting.message", null, locale);
    }
}
