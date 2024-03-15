package com.JokeGenClient.client;

import com.JokeGenClient.form.AddJokesForm;
import com.JokeGenClient.form.LoginForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

public interface JokeGenInterface {



    @GetExchange("/jokes")
    ResponseEntity<?>getJokes(String jwtToken);


    @PostExchange("/jokes")
    ResponseEntity<?>postJokes(String jwtToken, AddJokesForm addJokesForm);

    @GetExchange("/jokes/{id}")
    ResponseEntity<?> getAJokes(@PathVariable int id,String jwtToken);




}
