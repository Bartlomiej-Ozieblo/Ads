package com.adds.controller;

import com.adds.domain.Contact;
import com.adds.domain.Role;
import com.adds.domain.User;
import com.adds.repository.ContactRepository;
import com.adds.repository.RoleRepository;
import com.adds.repository.UserRepository;
import com.adds.utils.PasswordUtil;
import com.adds.utils.RegisterPlaceholder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.NoSuchAlgorithmException;

@Controller
@RequestMapping(value = "/register")
public class RegisterController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private RoleRepository roleRepository;

    @RequestMapping
    public String showRegisterForm(ModelMap model) {
        model.addAttribute("register-placeholder", new RegisterPlaceholder());
        return "register";
    }

    @RequestMapping(value = "/now", method = RequestMethod.POST)
    public String register(@ModelAttribute RegisterPlaceholder registerPlaceholder, ModelMap model) {
        User user = registerPlaceholder.getUser();
        Contact contact = registerPlaceholder.getContact();

        if (!user.getUserPassword().equals(registerPlaceholder.getRepeatPassword())) {
            return "redirect:/register";
        }

        if (user.getUserPassword().length() < 5) {
            return "redirect:/register";
        }

        if (contact.getEmail() == null) {
            return "redirect:/register";
        }

        Role userRole = roleRepository.findOne(2);

        try {
            user.setUserPassword(PasswordUtil.getInstance().textPasswordToHash(user.getUserPassword()));
            user.setRole(userRole);
            user.setContact(contact);

            contactRepository.save(contact);
            userRepository.save(user);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return "redirect:/";
    }
}
