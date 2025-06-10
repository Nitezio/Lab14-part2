package com.csc3402.security.form_login.controller;

import com.csc3402.security.form_login.dto.UserDto;
import com.csc3402.security.form_login.model.User;
import com.csc3402.security.form_login.service.UserService;
import jakarta.validation.Valid;                                    // changed from @Validated
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Arrays;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserDto());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(
            @Valid @ModelAttribute("user") UserDto userDto,
            BindingResult result,
            Model model) {

        User existingUser = userService.findUserByEmail(userDto.getEmail());
        if (existingUser != null) {
            result.rejectValue("email", null, "User already registered!");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
            return "registration";
        }
        userService.saveUser(userDto);
        return "redirect:/registration?success";
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handleNoHandler(NoHandlerFoundException ex, Model model) {
        model.addAttribute("status", ex.getHttpMethod() + " " + ex.getRequestURL());
        model.addAttribute("errorMessage", ex.getMessage());
        model.addAttribute("stackTraceLines",
                Arrays.stream(ex.getStackTrace())
                        .map(StackTraceElement::toString)
                        .toArray(String[]::new));
        return "error";
    }
}
