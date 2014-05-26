package com.ads.controller;

import com.ads.domain.Ad;
import com.ads.domain.Category;
import com.ads.repository.AdDAO;
import com.ads.repository.CategoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    private CategoryDAO categoryRepository;

    @Autowired
    private AdDAO adRepository;

    @RequestMapping(value = "/")
    public String showAllCategories(ModelMap model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "categories";
    }

    @RequestMapping(value = "/admin/categories")
    public String showCategoriesPage(ModelMap modelMap) {
        modelMap.addAttribute("categories", categoryRepository.findAll());
        modelMap.addAttribute("category-entity", new Category());
        return "adminCategories";
    }

    @RequestMapping(value = "/admin/category/add", method = RequestMethod.POST)
    public String addCategory(@ModelAttribute Category category) {
        categoryRepository.save(category);
        return "redirect:/admin/categories";
    }

    @RequestMapping(value = "/admin/category/id/{id}/remove")
    public String removeCategory(@PathVariable("id") Integer categoryId) {
        List<Ad> allAds = adRepository.findAll();
        List<Ad> ads = new ArrayList<Ad>();
        for (Ad ad : allAds) {
            if (ad.getCategory().getId().equals(categoryId)) {
                ads.add(ad);
            }
        }

        adRepository.delete(ads);
        Category category = categoryRepository.findOne(categoryId);
        categoryRepository.delete(category);

        return "redirect:/admin/categories";
    }

}
