package com.adds.controller;

import com.adds.domain.Ad;
import com.adds.domain.Category;
import com.adds.domain.Role;
import com.adds.domain.User;
import com.adds.repository.AdRepository;
import com.adds.repository.CategoryRepository;
import com.adds.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Controller
public class AdsController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AdRepository adRepository;

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

        model.addAttribute("ad", ad);
        model.addAttribute("user", user);

        return "ad";
    }

    @RequestMapping(value = "ads/user/{id}")
    public String showUserAds(@PathVariable("id") Integer id, ModelMap model) {
        User user = userRepository.findOne(id);

        model.addAttribute("user", user);

        return "ads";
    }

    //TODO: bad user name error handling and validation
    @RequestMapping(value = "user/{userName}/ad/edit/{id}/now", method = RequestMethod.POST)
    public String editAd(@PathVariable("userName") String userName, @PathVariable("id") Integer id, @ModelAttribute Ad modifiedAd, ModelMap model) {
        Ad ad = adRepository.findOne(id);
        ad.setCategory(modifiedAd.getCategory());
        ad.setCreateDate(new Date());
        ad.setTitle(modifiedAd.getTitle());
        ad.setText(modifiedAd.getText());

        adRepository.save(ad);

        return "redirect:/user/" + userName;
    }

    //TODO: bad user name and ad id error handling
    @RequestMapping(value = "/user/{userName}/ad/remove/{id}")
    public String removeAd(@PathVariable("userName") String userName, @PathVariable("id") Integer id, ModelMap model) {
        User user = userRepository.findByUserName(userName);
        Ad ad = adRepository.findOne(id);

        user.getAds().remove(ad);
        userRepository.save(user);
        adRepository.delete(ad);

        return "redirect:/user/" + userName;
    }

    //TODO: bad user name error handling and validating
    @RequestMapping(value = "/user/{userName}/ad/add/now", method = RequestMethod.POST)
    public String addAd(@PathVariable("userName") String userName, @ModelAttribute Ad ad, ModelMap model) {
        ad.setCreateDate(new Date());

        Category category = categoryRepository.findOne(ad.getCategory().getId());
        ad.setCategory(category);

        User user = userRepository.findByUserName(userName);
        user.getAds().add(ad);

        adRepository.save(ad);
        userRepository.save(user);

        if (user.getRole().getRoleName().equals(Role.ROLE_ADMIN)) {
            return "redirect:/admin/" + user.getUserName() + "?success=true";
        }

        return "redirect:/user/" + userName + "?success=true";
    }
}
