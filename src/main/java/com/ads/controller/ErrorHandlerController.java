package com.ads.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping   ;

@Controller
public class ErrorHandlerController {

    @RequestMapping(value = "/denied")
    public String accessDenied() {
        return "accessDenied";
    }

    @RequestMapping(value = "/login")
    public String loginNecessary() {
        return "login";
    }
}
