package com.JokeGenClient.client;

import com.JokeGenClient.form.LoginForm;
import com.JokeGenClient.form.SignupForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.service.annotation.PostExchange;

public interface AuthInterface {

    @PostExchange("/jokes/login")
    ResponseEntity<?> login(LoginForm login);

    @PostExchange("/jokes/register")
    ResponseEntity<?> signup(SignupForm signupForm);

}
