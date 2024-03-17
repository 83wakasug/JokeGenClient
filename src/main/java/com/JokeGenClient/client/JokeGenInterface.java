package com.JokeGenClient.client;

import com.JokeGenClient.form.AddJokesForm;
import com.JokeGenClient.form.AuthorDTO;
import com.JokeGenClient.form.LoginForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;
import org.springframework.web.service.annotation.PutExchange;

public interface JokeGenInterface {



    @GetExchange("/jokes")
    ResponseEntity<?>getJokes(String jwtToken);


    @PostExchange("/jokes")
    ResponseEntity<?>postJokes(String jwtToken,@RequestBody AddJokesForm addJokesForm);

    @GetExchange("/jokes/{id}")
    ResponseEntity<?> getAJokes(@PathVariable int id,String jwtToken);

    @PutExchange("/jokes")
    ResponseEntity<?>updateJokes(String jwtToken, @RequestBody AuthorDTO authorDTO);

    @DeleteExchange("/jokes/{id}")
    ResponseEntity<?> deleteJokes(@PathVariable int id,String jwtToken);


}
