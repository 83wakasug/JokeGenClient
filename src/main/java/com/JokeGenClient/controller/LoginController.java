package com.JokeGenClient.controller;

import com.JokeGenClient.form.LoginForm;
import com.JokeGenClient.form.SignupForm;
import com.JokeGenClient.form.UserData;
import com.JokeGenClient.service.AuthService;
import com.JokeGenClient.service.UserService;
import com.JokeGenClient.token.DecodeToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.client.HttpClientErrorException;

@Controller
@SessionAttributes("userData")
@RequestMapping("/jokes")
@RequiredArgsConstructor
public class LoginController {
    private final AuthService authService;
    private final DecodeToken decode;
    private final UserService userService;
    private final String ERRORMSG="wrong username or password";
    @GetMapping("/login")
    public String loginView(Model model,@ModelAttribute @Validated LoginForm login) {
        model.addAttribute("login",login);return "login";
    }

    @PostMapping("/login")
    public String login(Model model, @ModelAttribute @Validated LoginForm login, HttpSession session) throws JsonProcessingException {
        model.addAttribute("login", login);

        ResponseEntity<?> response = null;
        try {
            response = authService.login(login);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().equals(HttpStatus.UNAUTHORIZED)) {
                model.addAttribute("errorMsg", "Wrong username or Password");
                return "login";
            }
        }

        if (response != null && response.getStatusCode().is2xxSuccessful()) {
            // Get the response body
            Object responseBody = response.getBody();
            if (responseBody != null) {
                // Convert the response body to a string
                String json = responseBody.toString();
                UserData userData = createSessionData(json);
                session.setAttribute("userData", userData);
                return "redirect:/jokes/index";
            }

        }
        model.addAttribute("errorMsg", "Please enter correct username and password");
        return "login";
    }

    @GetMapping("/signup")
    public String signupView(Model model,@ModelAttribute @Validated SignupForm signupForm) {
        model.addAttribute("signup", signupForm);return "signup";

    }

    @PostMapping("/signup")
    public String signup(Model model, @ModelAttribute @Validated SignupForm signupForm, BindingResult bdResult) {
       try {
           var response = authService.signup(signupForm);
           model.addAttribute("signup", signupForm);

               return "redirect:/jokes/login";


       }catch(HttpClientErrorException e)
       {
           if (e.getStatusCode().equals(HttpStatus.UNAUTHORIZED)) {
               model.addAttribute("errorMsg", "Username already exists or Password does not meet the requirements.");
       }
        return "signup";
        }
    }


    public UserData createSessionData(String json) throws JsonProcessingException {;
        UserData user =new ObjectMapper().readValue(json,UserData.class);
        user.setUsername(decode.parseJason(user.getToken(),"sub"));
        user.setRoles(decode.parseJason(user.getToken(),"roles"));
        return user;
    }

    @GetMapping("/logout")
    public String logout(Model model, @ModelAttribute @Validated LoginForm login, SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "redirect:/jokes/login";
    }

}
