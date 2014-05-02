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

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;

@Controller
public class RegisterController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private RoleRepository roleRepository;

    @RequestMapping(value = "/register")
    public String showRegisterForm(ModelMap model) {
        model.addAttribute("register-placeholder", new RegisterPlaceholder());
        return "register";
    }

    @RequestMapping(value = "/register/now", method = RequestMethod.POST)
    public String register(@ModelAttribute RegisterPlaceholder registerPlaceholder, HttpServletRequest request) {
        User user = registerPlaceholder.getUser();
        Contact contact = user.getContact();

        if (user.getUserName() == null || user.getUserName().isEmpty()) {
            return "redirect:/register?error=0";
        }

        User existingUser = userRepository.findByUserName(user.getUserName());
        if (existingUser != null) {
            return "redirect:/register?error=1";
        }

        if (registerPlaceholder.getRepeatPassword() == null || registerPlaceholder.getRepeatPassword().isEmpty() ||
                !user.getUserPassword().equals(registerPlaceholder.getRepeatPassword())) {
            return "redirect:/register?error=2";
        }

        if (user.getUserPassword().length() < 5) {
            return "redirect:/register?error=3";
        }

        if (contact.getEmail() == null || contact.getEmail().isEmpty()) {
            return "redirect:/register?error=4";
        }

        Role userRole = roleRepository.findOne(Role.ROLE_USER_ID);

        try {
            user.setUserPassword(PasswordUtil.getInstance().textPasswordToHash(user.getUserPassword()));
            user.setRole(userRole);
            user.setContact(contact);

            contactRepository.save(contact);
            userRepository.save(user);

            return "redirect:/?register=true";
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();

            return "redirect:/?register=false";
        }
    }
}
