package com.ads.controller;

import com.ads.domain.Ad;
import com.ads.domain.Category;
import com.ads.domain.Role;
import com.ads.domain.User;
import com.ads.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AdRepository adRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ContactRepository contactRepository;

    @RequestMapping(value = "/admin/users")
    public String showAllUsers(ModelMap model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);

        return "adminUsers";
    }

    @RequestMapping(value = "/admin/user/remove/id/{id}")
    public String removeUser(@PathVariable("id") Integer id) {
        User user = userRepository.findOne(id);
        userRepository.delete(user);
        adRepository.delete(user.getAds());
        contactRepository.delete(user.getContact());

        return "redirect:/admin/users";
    }

    @RequestMapping(value = "/admin/user/role/admin/{id}")
    public String makeUserAdmin(@PathVariable("id") Integer id, HttpServletRequest request) {
        User user = userRepository.findOne(id);
        Role adminRole = roleRepository.findOne(Role.ROLE_ADMIN_ID);

        user.setRole(adminRole);

        userRepository.save(user);

        return "redirect:" + request.getHeader("referer");
    }

    @RequestMapping(value = "/admin/user/id/{id}")
    public String showUserPage(@PathVariable("id") Integer userId, ModelMap modelMap) {
        User user = userRepository.findOne(userId);
        if (user != null) {
            modelMap.addAttribute("user", user);
        } else {
            return "redirect:/admin/users";
        }

        return "adminUser";
    }

    @RequestMapping(value = "/admin/ads")
    public String showAllAd(ModelMap modelMap) {
        modelMap.addAttribute("categories", categoryRepository.findAll());
        modelMap.addAttribute("users", userRepository.findAll());
        modelMap.addAttribute("currentUsers", userRepository.findAll());

        return "adminAds";
    }

    @RequestMapping(value = "/admin/ads/user/{userId}")
    public String filterAdsByUser(@PathVariable("userId") Integer userId, ModelMap modelMap) {
        modelMap.addAttribute("categories", categoryRepository.findAll());
        modelMap.addAttribute("users", userRepository.findAll());
        modelMap.addAttribute("currentUsers", Arrays.asList(userRepository.findOne(userId)));
        modelMap.addAttribute("userFilter", true);
        modelMap.addAttribute("categoryFilter", false);

        return "adminAds";
    }

    @RequestMapping(value = "/admin/ads/category/{categoryId}")
    public String filterAdsByCategory(@PathVariable("categoryId") Integer categoryId, ModelMap modelMap) {
        Category currentCategory = categoryRepository.findOne(categoryId);
        modelMap.addAttribute("currentCategory", currentCategory);
        modelMap.addAttribute("categories", categoryRepository.findAll());
        modelMap.addAttribute("users", userRepository.findAll());
        modelMap.addAttribute("currentUsers", userRepository.findAll());
        modelMap.addAttribute("userFilter", false);
        modelMap.addAttribute("categoryFilter", true);

        return "adminAds";
    }

    @RequestMapping(value = "/admin/ads/user/{userId}/category/{categoryId}")
    public String filterAds(@PathVariable("userId") Integer userId, @PathVariable("categoryId") Integer categoryId, ModelMap modelMap) {
        modelMap.addAttribute("currentCategory", categoryRepository.findOne(categoryId));
        modelMap.addAttribute("categories", categoryRepository.findAll());
        modelMap.addAttribute("users", userRepository.findAll());
        modelMap.addAttribute("currentUsers", Arrays.asList(userRepository.findOne(userId)));
        modelMap.addAttribute("userFilter", true);
        modelMap.addAttribute("categoryFilter", true);

        return "adminAds";
    }


    @RequestMapping(value = "/admin/ad/id/{id}/remove")
    public String removeAd(@PathVariable("id") Integer adId, HttpServletRequest request) {
        adRepository.delete(adId);
        return "redirect:" + request.getHeader("referer");
    }
}
