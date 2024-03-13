package com.JokeGenClient.controller;

import com.JokeGenClient.form.JokesForm;
import com.JokeGenClient.form.LoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/jokes")
public class GeneralController {

    @GetMapping("/login")
    public String getLogin() {
        return "index";
    }

    @GetMapping("/add")
    public String addAJoke() {
        return "addJokes";
    }

    @PostMapping("/login")
    public String login(Model model, LoginForm login) {
        model.addAttribute("login",login);
        return "index";
    }

    @PostMapping("/add")
    public String addJoke(Model model, JokesForm jokesForm) {
        model.addAttribute("jokes", jokesForm);
        return "index";
    }

}
