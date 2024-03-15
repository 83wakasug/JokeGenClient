package com.JokeGenClient.controller;

import com.JokeGenClient.form.JokesDTO;
import com.JokeGenClient.form.JokesForm;
import com.JokeGenClient.form.UserData;
import com.JokeGenClient.service.AuthService;
import com.JokeGenClient.service.GeneralService;
import com.JokeGenClient.token.DecodeToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

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

    @GetMapping("/list")
    public String jokesList(Model model,@ModelAttribute("userData")UserData userData, JokesDTO jokesDTO) {

        try {
            ResponseEntity<?>responseEntity=generalService.getJokes(userData.getToken());
            ArrayList<JokesDTO>jokesList= (ArrayList<JokesDTO>) responseEntity.getBody();
            model.addAttribute("jokes", jokesList);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return "listJokes";
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
