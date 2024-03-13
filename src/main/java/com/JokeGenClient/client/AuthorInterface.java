package com.JokeGenClient.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;
import org.springframework.web.service.annotation.PutExchange;

import java.util.List;


public interface AuthorInterface {




    @GetExchange("/{id}")
    ResponseEntity<?> getAuthor(String jwtToken);

    @GetExchange
    ResponseEntity<List> getAuthors(String jwtToken);


    @PostExchange("/")
    ResponseEntity<?> addAuthor(String jwtToken);

    @DeleteExchange("/{id}")
    ResponseEntity<?> deleteAuthor(String jwtToken);

    @PutExchange("/{id}")
    ResponseEntity<?> updateAuthor(String jwtToken);

}
