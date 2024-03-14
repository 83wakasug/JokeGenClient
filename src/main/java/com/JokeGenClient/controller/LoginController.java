package com.JokeGenClient.controller;

import com.JokeGenClient.form.LoginForm;
import com.JokeGenClient.form.SignupForm;
import com.JokeGenClient.form.UserData;
import com.JokeGenClient.service.AuthService;
import com.JokeGenClient.token.DecodeToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("userData")
@RequestMapping("/jokes")
@RequiredArgsConstructor
public class LoginController {
    private final AuthService authService;
    private final DecodeToken decode;

    @GetMapping("/login")
    public String loginView(Model model,@ModelAttribute @Validated LoginForm login) {
        model.addAttribute("login",login);return "login";
    }

    @PostMapping("/login")
    public String login(Model model, @ModelAttribute @Validated LoginForm login) throws JsonProcessingException {
        model.addAttribute("login",login);

        ResponseEntity<?> response = authService.login(login);
        if (response.getStatusCode().is2xxSuccessful()) {
            // Get the response body
            Object responseBody = response.getBody();
            if (responseBody != null) {
                // Convert the response body to a string
                String json = responseBody.toString();
                createSessionData(json);
            } else {
                System.out.println("Response body is null");
            }
        } else {
            System.out.println("Login request failed with status code: " + response.getStatusCodeValue());
        }


        return "redirect:/jokes/index";
    }

    @GetMapping("/signup")
    public String signupView(Model model,@ModelAttribute @Validated SignupForm signupForm) {
        model.addAttribute("signup", signupForm);return "signup";


    }

    @PostMapping("/signup")
    public String signup(Model model,@ModelAttribute @Validated SignupForm signupForm) {
        model.addAttribute("signup", signupForm);return "signup";
        authService.


    }


    public void createSessionData(String json) throws JsonProcessingException {;
        UserData user =new ObjectMapper().readValue(json,UserData.class);
        user.setUsername(decode.parseJason(user.getToken(),"sub"));
        user.setRoles(decode.parseJason(user.getToken(),"roles"));

    }

}
