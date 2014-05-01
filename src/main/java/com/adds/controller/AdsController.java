package com.adds.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdsController {

    @RequestMapping(value = "/ads/add")
    public String showAddAdForm(ModelMap model) {
        return "showAddAdForm";
    }
}
