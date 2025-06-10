package com.csc3402.security.form_login.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Arrays;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping
    public String userHome(Model model) {
        // Grab the current Authentication (guaranteed non-null for authenticated pages)
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String username = auth.getName();  // e.g. "nani@gmail.com"

        // Collect all authorities (roles), e.g. ["ROLE_ADMIN", "ROLE_USER"]
        String allRoles = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .sorted()                         // optional: deterministic order
                .collect(Collectors.joining(", "));

        // Wrap with [ ... ]
        model.addAttribute("username", username);
        model.addAttribute("roles", "[" + allRoles + "]");

        return "user";
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
