package com.JokeGenClient.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;
import org.springframework.web.service.annotation.PutExchange;

public interface JokeGenInterface {



    @GetExchange("/")
    ResponseEntity<?>getJokes();

    @PutExchange("/")
    ResponseEntity<?>putJokes();

    @PostExchange("/")
    ResponseEntity<?>postJokes();

    @GetExchange("/{id}")
    ResponseEntity<?> getAJokes(@PathVariable int id);

    @DeleteExchange("/{id}")
    ResponseEntity<?> deleteJokes(@PathVariable int id);

    @GetExchange("/random")
    ResponseEntity<?> generateRandomJokes();


}
