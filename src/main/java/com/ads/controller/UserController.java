package com.ads.controller;

import com.ads.domain.Ad;
import com.ads.domain.Category;
import com.ads.domain.Role;
import com.ads.domain.User;
import com.ads.repository.AdRepository;
import com.ads.repository.CategoryRepository;
import com.ads.repository.ContactRepository;
import com.ads.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
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

    @RequestMapping(value = "/user")
    public String userAdsMediator(ModelMap modelMap) {
        String userName =
                ((org.springframework.security.core.userdetails.User)
                        SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();

        User user = userRepository.findByUserName(userName);

        if (isAdmin()) {
            modelMap.addAttribute("admin", true);
        }

        List<Ad> ads = new ArrayList<Ad>(user.getAds());
        modelMap.addAttribute("ads", ads);

        return "userAds";
    }

    @RequestMapping(value = "/user/ad/add")
    public String showAddAdForm(ModelMap modelMap) {
        if (isAdmin()) {
            modelMap.addAttribute("admin", true);
        }

        List<Category> categories = categoryRepository.findAll();
        modelMap.addAttribute("categories", categories);
        modelMap.addAttribute("ad_entity", new Ad());
        modelMap.addAttribute("edit", false);

        return "addAd";
    }

    @RequestMapping(value = "/user/ad/edit/{id}")
    public String showEditAdForm(@PathVariable("id") Integer id, ModelMap model) {
        Ad ad = adRepository.findOne(id);

        if (isAdmin()) {
            model.addAttribute("admin", true);
        }

        model.addAttribute("ad_entity", ad);
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("edit", true);

        return "addAd";
    }

    @RequestMapping(value = "/user/info")
    public String showEditInfoForm(ModelMap model) {
        String userName =
                ((org.springframework.security.core.userdetails.User)
                        SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();

        User user = userRepository.findByUserName(userName);

        if (isAdmin()) {
            model.addAttribute("admin", true);
        }

        model.addAttribute("user_entity", user);

        return "userInfo";
    }

    //TODO: info validation + changing password
    @RequestMapping(value = "/user/info/edit", method = RequestMethod.POST)
    public String editInfo(@ModelAttribute User modifiedUser) {
        String userName =
                ((org.springframework.security.core.userdetails.User)
                        SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();

        User user = userRepository.findByUserName(userName);
        user.setContact(modifiedUser.getContact());

        contactRepository.save(modifiedUser.getContact());
        userRepository.save(user);

        return "redirect:/user/info";
    }

    private boolean isAdmin() {
        String userName =
                ((org.springframework.security.core.userdetails.User)
                        SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();

        if (userName == null) {
            return false;
        } else {
            Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
            for (GrantedAuthority authority : authorities) {
                if (authority.getAuthority().equals(Role.ROLE_ADMIN)) {
                    return true;
                }
            }

            return false;
        }
    }
}
