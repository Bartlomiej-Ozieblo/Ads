package com.ads.controller;

import com.ads.domain.Ad;
import com.ads.domain.Category;
import com.ads.domain.Role;
import com.ads.domain.User;
import com.ads.repository.AdDAO;
import com.ads.repository.CategoryDAO;
import com.ads.repository.ContactDAO;
import com.ads.repository.UserDAO;
import com.ads.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserDAO userRepository;

    @Autowired
    private AdDAO adRepository;

    @Autowired
    private CategoryDAO categoryRepository;

    @Autowired
    private ContactDAO contactRepository;

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

    @RequestMapping(value = "/user/ad/edit/id/{id}")
    public String showEditAdForm(@PathVariable("id") Integer id, ModelMap modelMap) {
        if (isAdmin()) {
            modelMap.addAttribute("admin", true);
        }

        Ad ad = adRepository.findOne(id);
        List<Category> categories = categoryRepository.findAll();
        modelMap.addAttribute("categories", categories);
        modelMap.addAttribute("ad_entity", ad);
        modelMap.addAttribute("edit", true);

        return "addAd";
    }

    @RequestMapping(value = "/user/info")
    public String showEditInfoForm(ModelMap model) {
        String userName =
                ((org.springframework.security.core.userdetails.User)
                        SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();

        User user = userRepository.findByUserName(userName);
        user.setUserPassword("FAKE");

        if (isAdmin()) {
            model.addAttribute("admin", true);
        }

        model.addAttribute("user_entity", user);

        return "userInfo";
    }

    @RequestMapping(value = "/user/info/edit", method = RequestMethod.POST)
    public String editInfo(@ModelAttribute User modifiedUser) {
        String userName =
                ((org.springframework.security.core.userdetails.User)
                        SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();

        if (modifiedUser.getContact().getEmail() == null || modifiedUser.getContact().getEmail().isEmpty()) {
            return "redirect:/user/info?error=0";
        }

        User user = userRepository.findByUserName(userName);
        user.setContact(modifiedUser.getContact());

        String newPassword = modifiedUser.getUserPassword();
        if (!newPassword.equals("FAKE")) {
            if (newPassword.isEmpty() || newPassword.length() < 5) {
                return "redirect:/user/info?error=1";
            }

            String hashPassword = null;
            try {
                hashPassword = PasswordUtil.getInstance().textPasswordToHash(modifiedUser.getUserPassword());
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

            if (hashPassword != null) {
                if (hashPassword.equals(user.getUserPassword())) {
                    return "redirect:/user/info?error=2";
                } else {
                    user.setUserPassword(hashPassword);
                }
            } else {
                return "redirect:/user/info?error=3";
            }
        }

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
