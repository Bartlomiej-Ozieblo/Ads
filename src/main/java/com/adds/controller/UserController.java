package com.adds.controller;

import com.adds.domain.Contact;
import com.adds.domain.Role;
import com.adds.domain.User;
import com.adds.repository.ContactRepository;
import com.adds.repository.RoleRepository;
import com.adds.repository.UserRepository;
import com.adds.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.NoSuchAlgorithmException;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ContactRepository contactRepository;

    @RequestMapping(value = "/")
    public String listUsers(ModelMap modelMap) {
        modelMap.addAttribute("users", userRepository.findAll());
        modelMap.addAttribute("user-entity", new User());

        return "index";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute User user) {
        Role userRole = roleRepository.findOne(2);
        user.setRole(userRole);

        try {
            user.setUserPassword(PasswordUtil.getInstance().textPasswordToHash(user.getUserPassword()));
            userRepository.save(user);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return "redirect:/";
    }

    @RequestMapping(value = "/delete")
    public String deleteUser(@RequestParam(value = "id") Integer userId) {
        User user = userRepository.findOne(userId);
        Contact contact = user.getContact();

        userRepository.delete(user);
        contactRepository.delete(contact);

        return "redirect:/";
    }
}
