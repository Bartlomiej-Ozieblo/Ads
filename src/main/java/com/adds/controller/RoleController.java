package com.adds.controller;

import com.adds.domain.Role;
import com.adds.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;

	@RequestMapping(value = "/roles", method = RequestMethod.GET)
	public String listRoles(ModelMap model) {
        model.addAttribute("roles", roleRepository.findAll());
		return "hello";
	}

    @RequestMapping(value = "/roles/add", method = RequestMethod.POST)
    public String addRole(@ModelAttribute("role") Role role, BindingResult result) {
        roleRepository.save(role);

        return "redirect:/";
    }

    @RequestMapping(value = "/roles/delete", method =  RequestMethod.POST)
    public String deleteRole(@PathVariable("id") Integer id) {
        roleRepository.delete(id);

        return "redirect:/";
    }
}