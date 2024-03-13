package com.JokeGenClient.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PutExchange;

public interface AdminInterface {

    @PutExchange("/")
    ResponseEntity<?>updateJokes();

    @PutExchange("/{id}")
    ResponseEntity<?>putAuthor();

    @DeleteExchange("/{id}")
    ResponseEntity<?> deleteJokes(@PathVariable int id);

    @DeleteExchange("/{id}")
    ResponseEntity<?> deleteAuthor(@PathVariable int id);

    @GetExchange("/")
    ResponseEntity<?>getAllUsers();
    @GetExchange("/{id}")
    ResponseEntity<?>getAUser();


    @DeleteExchange("/{id}")
    ResponseEntity<?> deleteAUser(@PathVariable int id);

    @PutExchange("/{id}")
    ResponseEntity<?> updateAUser(@PathVariable int id);

    @GetExchange("/random")
    ResponseEntity<?> generateRandomJokes();




}
