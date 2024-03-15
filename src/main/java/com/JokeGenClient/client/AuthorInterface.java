package com.JokeGenClient.client;

import com.JokeGenClient.form.AuthorDTO;
import com.JokeGenClient.form.AuthorForm;
import com.JokeGenClient.form.JokesForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;
import org.springframework.web.service.annotation.PutExchange;

import java.util.List;


public interface AuthorInterface {


    @GetExchange("/author/{id}")
    ResponseEntity<?> getAuthor(String jwtToken);

    @GetExchange("/author")
    ResponseEntity<List> getAuthors(String jwtToken);


    @PostExchange("/author")
    ResponseEntity<?> addAuthor(String jwtToken, AuthorForm authorForm);

    @DeleteExchange("/author/{id}")
    ResponseEntity<?> deleteAuthor(String jwtToken);

    @PutExchange("/author/{id}")
    ResponseEntity<?> updateAuthor(String jwtToken);

}
