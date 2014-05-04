package com.adds.controller;

import com.adds.domain.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
public class AdminController {

    @RequestMapping(value = "/admin")
    public String interceptUrl() {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() == null) {
            return "redirect:/";
        }

        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals(Role.ROLE_ADMIN)) {
                return "redirect:/user";
            }
        }

        return "redirect:/";
    }
}
