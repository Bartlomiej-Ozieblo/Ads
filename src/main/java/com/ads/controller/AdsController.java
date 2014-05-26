package com.ads.controller;

import com.ads.domain.Ad;
import com.ads.domain.Category;
import com.ads.domain.Role;
import com.ads.domain.User;
import com.ads.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Controller
public class AdsController {

    @Autowired
    private UserDAO userRepository;

    @Autowired
    private CategoryDAO categoryRepository;

    @Autowired
    private AdDAO adRepository;

    @RequestMapping(value = "/category/{id}")
    public String showAdsFromCategory(@PathVariable("id") Integer id, ModelMap model) {
        Category category = categoryRepository.findOne(id);
        List<Ad> allAds = adRepository.findAll();

        List<Ad> ads = new LinkedList<Ad>();
        for (Ad ad : allAds) {
            if (ad.getCategory().getId().equals(category.getId())) {
                ads.add(ad);
            }
        }

        List<User> users = new LinkedList<User>();
        List<User> allUsers = userRepository.findAll();
        for (Ad ad : ads) {
            for (User user : allUsers) {
                for (Ad innerAd : user.getAds()) {
                    if (innerAd.getId().equals(ad.getId())) {
                        users.add(user);
                        break;
                    }
                }
            }
        }

        model.addAttribute("ads", ads);
        model.addAttribute("category", category);
        model.addAttribute("users", users);

        return "category";
    }

    @RequestMapping(value = "/ad/{id}")
    public String showAd(@PathVariable("id") Integer id, ModelMap model) {
        Ad ad = adRepository.findOne(id);
        if (ad != null) {

            List<User> users = userRepository.findAll();
            User user = null;
            for (User tmpUser : users) {
                for (Ad tmpAd : tmpUser.getAds()) {
                    if (tmpAd.getId().equals(id)) {
                        user = tmpUser;
                        break;
                    }
                }
            }

            if (isAdmin()) {
                if (user != null) {
                    String userName =
                            ((org.springframework.security.core.userdetails.User)
                                    SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();

                    if (user.getRole().getId() == Role.ROLE_ADMIN_ID && !user.getUserName().equals(userName)) {
                        model.addAttribute("admin", false);
                    } else if (user.getUserName().equals(userName)) {
                        model.addAttribute("admin", true);
                    } else {
                        model.addAttribute("admin", true);
                    }
                }
            }

            model.addAttribute("ad", ad);
            model.addAttribute("user", user);

            return "ad";
        }

        return "adNotFound";
    }

    @RequestMapping(value = "ads/user/{id}")
    public String showUserAds(@PathVariable("id") Integer id, ModelMap model) {
        User user = userRepository.findOne(id);

        model.addAttribute("user", user);

        return "ads";
    }

    @RequestMapping(value = "/ad/add", method = RequestMethod.POST)
    public String addAd(@ModelAttribute Ad ad) {
        String userName =
                ((org.springframework.security.core.userdetails.User)
                        SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();

        ad.setCreateDate(new Date());

        Category category = categoryRepository.findOne(ad.getCategory().getId());
        ad.setCategory(category);

        User user = userRepository.findByUserName(userName);
        user.getAds().add(ad);

        adRepository.save(ad);
        userRepository.save(user);

        return "redirect:/user/?success=true";
    }

    @RequestMapping(value = "/ad/edit/id/{id}", method = RequestMethod.POST)
    public String editAd(@PathVariable("id") Integer id, @ModelAttribute Ad modifiedAd) {
        Ad ad = adRepository.findOne(id);
        ad.setCategory(modifiedAd.getCategory());
        ad.setCreateDate(new Date());
        ad.setTitle(modifiedAd.getTitle());
        ad.setText(modifiedAd.getText());

        adRepository.save(ad);

        return "redirect:/user";
    }

    @RequestMapping(value = "/ad/remove/id/{id}")
    public String removeAd(@PathVariable("id") Integer id, ModelMap model) {
        String userName =
                ((org.springframework.security.core.userdetails.User)
                        SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();

        User user = userRepository.findByUserName(userName);
        Ad ad = adRepository.findOne(id);

        if (ad != null) {
            user.getAds().remove(ad);
            userRepository.save(user);
            adRepository.delete(ad);
        }

        return "redirect:/user";
    }

    private boolean isAdmin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
            for (GrantedAuthority authority : authorities) {
                if (authority.getAuthority().equals(Role.ROLE_ADMIN)) {
                    return true;
                }
            }

            return false;
        }

        return false;
    }

    @RequestMapping(value = "/admin/ad/id/{id}/remove")
    public String removeAd(@PathVariable("id") Integer adId, HttpServletRequest request) {
        Ad ad = adRepository.findOne(adId);
        adRepository.delete(ad);
        return "redirect:" + request.getHeader("referer");
    }
}
