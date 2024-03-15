package com.JokeGenClient.controller;

import com.JokeGenClient.form.AuthorForm;
import com.JokeGenClient.form.LoginForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("userData")
@RequestMapping("/jokes")
@RequiredArgsConstructor

public class AuthourController {
    @GetMapping("/author")
    public String authourListView(Model model, @ModelAttribute @Validated AuthorForm author) {
        model.addAttribute("author",author);

        return "author";
    }

}
