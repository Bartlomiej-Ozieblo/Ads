package com.adds.controller;

import com.adds.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CategoriesController {

    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping(value = "/")
    public String showAllCategories(ModelMap model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "categories";
    }

    @RequestMapping(value = "/category/{categoryName}")
    public String showCategory(@PathVariable("categoryName") String categoryName) {
        return "categories";
    }
}
