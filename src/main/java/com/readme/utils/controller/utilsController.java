package com.readme.utils.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/utils-service")
@RequiredArgsConstructor
public class utilsController {

    private final Environment env;
    @GetMapping("/health_check")
    public String status() {
        return String.format("It's Working in User Service on PORT : %s"
            , env.getProperty("local.server.port"));
    }
}
