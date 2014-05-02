package com.adds.controller;

import com.adds.domain.Ad;
import com.adds.domain.Category;
import com.adds.domain.Role;
import com.adds.domain.User;
import com.adds.repository.AdRepository;
import com.adds.repository.CategoryRepository;
import com.adds.repository.ContactRepository;
import com.adds.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdRepository adRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ContactRepository contactRepository;

    //TODO: bad user name error handling
    @RequestMapping(value = "/user")
    public String userAdsMediator() {
        String userName =
                ((org.springframework.security.core.userdetails.User)
                        SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();

        if (userName != null) {
            return "redirect:/user/" + userName;
        } else {
            return "redirect:/";
        }
    }

    //TODO: bad user name error handling
    @RequestMapping(value = "/user/{userName}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String usersAds(@PathVariable("userName") String userName, ModelMap modelMap) {
        User user = userRepository.findByUserName(userName);

        if (user.getRole().getRoleName().equals(Role.ROLE_ADMIN)) {
            return "redirect:/admin/" + user.getUserName();
        }

        List<Ad> ads = new ArrayList<Ad>(user.getAds());
        modelMap.addAttribute("ads", ads);

        return "userAds";
    }

    //TODO: bad user name error handling
    @RequestMapping(value = "/user/{userName}/ad/add")
    public String showAddAdForm(@PathVariable("userName") String userName, ModelMap modelMap) {
        User user = userRepository.findByUserName(userName);

        if (user.getRole().getRoleName().equals("ROLE_ADMIN")) {
            return "redirect:/admin/" + user.getUserName() + "/ad/add";
        }

        List<Category> categories = categoryRepository.findAll();
        modelMap.addAttribute("categories", categories);
        modelMap.addAttribute("ad_entity", new Ad());
        modelMap.addAttribute("edit", false);

        return "addAd";
    }

    //TODO: bad user name error handling
    @RequestMapping(value = "user/{userName}/ad/edit/{id}")
    public String showEditAdForm(@PathVariable("userName") String userName, @PathVariable("id") Integer id, ModelMap model) {
        Ad ad = adRepository.findOne(id);

        model.addAttribute("ad_entity", ad);
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("edit", true);

        return "addAd";
    }

    //TODO: bad user name error handling
    @RequestMapping(value = "/user/{userName}/info")
    public String showEditInfoForm(@PathVariable("userName") String userName, ModelMap model) {
        User user = userRepository.findByUserName(userName);

        model.addAttribute("user_entity", user);

        return "userInfo";
    }

    //TODO: bad user name error handling + info validation + changing password
    @RequestMapping(value = "user/{userName}/info/edit", method = RequestMethod.POST)
    public String editInfo(@PathVariable("userName") String userName, @ModelAttribute User modifiedUser, ModelMap model) {
        User user = userRepository.findByUserName(userName);
        user.setContact(modifiedUser.getContact());

        contactRepository.save(modifiedUser.getContact());
        userRepository.save(user);

        return "redirect:/user/" + userName + "/info";
    }
}
