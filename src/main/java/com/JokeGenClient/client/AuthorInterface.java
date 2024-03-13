package com.JokeGenClient.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;


public interface AuthorInterface {



    @GetExchange("/{id}")
    ResponseEntity<?> getAuthor();
    @GetExchange("/")
    ResponseEntity<?> getAllAuthor();

    @PostExchange("/")
    ResponseEntity<?> addAuthor();

}
