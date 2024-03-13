package com.JokeGenClient.controller;

import com.JokeGenClient.form.JokesForm;
import com.JokeGenClient.form.LoginForm;
import com.JokeGenClient.form.UserData;
import com.JokeGenClient.service.GeneralService;
import com.JokeGenClient.token.DecodeToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    @GetMapping("/login")
    public String getLogin() {
        return "index";
    }

    @GetMapping("/add")
    public String addAJoke() {
        return "addJokes";
    }

    @PostMapping("/login")
    public String login(Model model, @ModelAttribute @Validated LoginForm login) throws JsonProcessingException {
        model.addAttribute("login",login);
        String json= String.valueOf(generalService.login(login));
        createSessionData(json);

        return "index";
    }

    @PostMapping("/add")
    public String addJoke(@ModelAttribute("userData")UserData userData,Model model, @ModelAttribute @Validated JokesForm jokesForm) {
        model.addAttribute("jokes", jokesForm);


        return "index";
    }

    public void createSessionData(String json) throws JsonProcessingException {
        UserData user =new ObjectMapper().readValue(json,UserData.class);
        user.setUsername(decode.parseJason(user.getToken(),"sub"));
        user.setRoles(decode.parseJason(user.getToken(),"roles"));
    }

}
