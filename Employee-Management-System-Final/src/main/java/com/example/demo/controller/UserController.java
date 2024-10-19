package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.model.User;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class UserController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String detail_login(Model model)
    {
        model.addAttribute("user",new User());
        return "registration";
    }
    @PostMapping("/save_details")
    public String saveDetails(User user,Model model)
    {
        userService.saveUser(user);
        model.addAttribute("message", "User registered successfully!");
        return "registration";
    }
    @PostMapping("/check")
    public String loginValidate(@Valid @ModelAttribute("user") User user, BindingResult result, Model model)
    {

        if(result.hasErrors())
        {
            System.out.println(result);
            model.addAttribute("message", "Invalid username or password.");
            return "login";
        }
        User existingUser = userService.findByUsername(user.getUsername());
        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
            model.addAttribute("message", "Login successful!");
            List<Employee> listEmployees = employeeService.getAllEmployees();
            model.addAttribute("listEmployees", listEmployees);
            return "index";
        }
        else {
            model.addAttribute("message", "Invalid username or password.");
            return "login";
        }
    }
}
