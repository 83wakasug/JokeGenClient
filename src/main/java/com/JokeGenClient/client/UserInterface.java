package com.JokeGenClient.client;

import com.JokeGenClient.form.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PutExchange;

import java.util.List;

public interface UserInterface {




    @GetExchange("/admin/{id}")
    ResponseEntity<?>getAUser(String jwtToken,@PathVariable int id);
    @GetExchange("/admin/")
    ResponseEntity<List>getUsers(String jwtToken);

    @DeleteExchange("/admin/{id}")
    ResponseEntity<?> deleteAUser(@PathVariable int id,String jwtToken);

    @PutExchange("/admin/{id}")
    ResponseEntity<?> updateAUser(@RequestBody UserDTO user, String jwtToken);

}
