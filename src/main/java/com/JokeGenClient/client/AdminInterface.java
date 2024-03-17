package com.JokeGenClient.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PutExchange;

import java.util.List;

public interface AdminInterface {

    @PutExchange("/jokes")
    ResponseEntity<?>updateJokes(String jwtToken);

    @DeleteExchange("/jokes/{id}")
    ResponseEntity<?> deleteJokes(@PathVariable int id,String jwtToken);

    @DeleteExchange("/author/{id}")
    ResponseEntity<?> deleteAuthor(@PathVariable int id,String jwtToken);
    @GetExchange("/admin/{id}")
    ResponseEntity<?>getAUser(String jwtToken);
    @GetExchange("/admin")
    ResponseEntity<List>getAUsers(String jwtToken);

    @DeleteExchange("/admin/{id}")
    ResponseEntity<?> deleteAUser(@PathVariable int id,String jwtToken);

    @PutExchange("/admin/{id}")
    ResponseEntity<?> updateAUser(@PathVariable int id,String jwtToken);


}
