package com.ads.controller;

import com.ads.domain.Category;
import com.ads.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping(value = "/")
    public String showAllCategories(ModelMap model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "categories";
    }
}
