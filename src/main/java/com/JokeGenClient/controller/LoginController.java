package com.JokeGenClient.controller;

import com.JokeGenClient.form.LoginForm;
import com.JokeGenClient.form.SignupForm;
import com.JokeGenClient.form.UserData;
import com.JokeGenClient.service.AuthService;
import com.JokeGenClient.token.DecodeToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
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
    private final String ERRORMSG="wrong username or password";
    @GetMapping("/login")
    public String loginView(Model model,@ModelAttribute @Validated LoginForm login) {
        model.addAttribute("login",login);return "login";
    }

    @PostMapping("/login")
    public String login(Model model, @ModelAttribute @Validated LoginForm login, HttpSession session) throws JsonProcessingException {
        model.addAttribute("login",login);

        ResponseEntity<?> response = authService.login(login);
        if (response.getStatusCode().is2xxSuccessful()) {
            // Get the response body
            Object responseBody = response.getBody();
            if (responseBody != null) {
                // Convert the response body to a string
                String json = responseBody.toString();
                UserData userData = createSessionData(json);
                model.addAttribute("userData", userData);
                return "redirect:/jokes/index";
            } else {
                model.addAttribute("errorMsg", "Login request failed with status code: " + response.getStatusCodeValue());
                System.out.println("Login request failed with status code: " + response.getStatusCodeValue());

            }
        } else {
            model.addAttribute("errorMsg", "Invalid username or password");
            System.out.println("Invalid username or password");

        }
        return "login";

    }

    @GetMapping("/signup")
    public String signupView(Model model,@ModelAttribute @Validated SignupForm signupForm) {
        model.addAttribute("signup", signupForm);return "signup";

    }

    @PostMapping("/signup")
    public String signup(Model model,@ModelAttribute @Validated SignupForm signupForm) {
        model.addAttribute("signup", signupForm);
        System.out.println(signupForm.getPassword()+signupForm.getUsername());
        var response = authService.signup(signupForm);
        if (response.getStatusCode().is2xxSuccessful()) {
            // 登録成功時のフラッシュメッセージ
            return "redirect:/jokes/login?success=User successfully registered!";
        } else {
            // 登録失敗時のフラッシュメッセージ
            return "redirect:/jokes/login?error=User registration failed. Please try again.";
        }
    }


    public UserData createSessionData(String json) throws JsonProcessingException {;
        UserData user =new ObjectMapper().readValue(json,UserData.class);
        user.setUsername(decode.parseJason(user.getToken(),"sub"));
        user.setRoles(decode.parseJason(user.getToken(),"roles"));
        return user;
    }

}
