package com.JokeGenClient.service;

import com.JokeGenClient.client.AuthInterface;
import com.JokeGenClient.client.JokeGenInterface;
import com.JokeGenClient.form.JokesForm;
import com.JokeGenClient.form.LoginForm;
import com.JokeGenClient.form.SignupForm;
import com.JokeGenClient.token.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class AuthService implements AuthInterface {

    private final RestClient restClient;
    private final Token tokenHeader;


    @Override
    public ResponseEntity<?> login(LoginForm login) {
        return restClient.post()
                .uri("/auth/login",login)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(login)
                .retrieve()
                .toEntity(String.class);
    }


    @Override
    public ResponseEntity<?> signup(SignupForm signupForm) {
        return restClient.post()
                .uri("/auth/register",signupForm)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(signupForm)
                .retrieve()
                .toEntity(String.class);
    }
}
