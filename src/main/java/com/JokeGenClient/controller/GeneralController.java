package com.JokeGenClient.controller;

import com.JokeGenClient.form.JokesForm;
import com.JokeGenClient.form.UserData;
import com.JokeGenClient.service.GeneralService;
import com.JokeGenClient.token.DecodeToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("userData")
@RequestMapping("/jokes")
@RequiredArgsConstructor
public class GeneralController {

    private final GeneralService generalService;
    private final DecodeToken decode;

    @GetMapping("/index")
    public String index(@ModelAttribute("userData")UserData userData ) {
        return "index";
    }
    @GetMapping("/create")
    public String getAJoke() {
        return "addJokes";
    }


    @PostMapping("/create")
    public String addJoke(@ModelAttribute("userData")UserData userData,Model model, @ModelAttribute @Validated JokesForm jokesForm) {
        model.addAttribute("jokes", jokesForm);


        return "login";
    }



}
