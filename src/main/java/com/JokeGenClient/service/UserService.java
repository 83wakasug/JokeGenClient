package com.JokeGenClient.service;

import com.JokeGenClient.client.UserInterface;
import com.JokeGenClient.form.UserDTO;
import com.JokeGenClient.token.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestClient;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService implements UserInterface {
    private final RestClient restClient;
    private final Token tokenHeader;
    private final String ADMIN="/admin/";
    private final String ADMINID="/admin/{id}";



    @Override
    public ResponseEntity<?> getAUser(String jwtToken, @PathVariable int id) {
        return restClient.get()
                .uri(ADMINID,id)
                .headers(httpHeaders -> httpHeaders.addAll(tokenHeader.createHeader(jwtToken)))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(UserDTO.class);
    }

    @Override
    public ResponseEntity<List> getUsers(String jwtToken) {
       return restClient.get()
                .uri(ADMIN)
                .headers(httpHeaders -> httpHeaders.addAll(tokenHeader.createHeader(jwtToken)))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(List.class);
    }

    @Override
    public ResponseEntity<?> deleteAUser(int id,String jwtToken) {
        return restClient.delete()
                .uri(ADMINID,id)
                .headers(httpHeaders -> httpHeaders.addAll(tokenHeader.createHeader(jwtToken)))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(UserDTO.class);
    }

    @Override
    public ResponseEntity<?> updateAUser(UserDTO user,String jwtToken) {
        Map<String, Object> uriVariables = Collections.singletonMap("id", user.getUserId());
        return restClient.put()
                .uri("/admin/toggleRights/{id}",uriVariables)
                .headers(httpHeaders -> httpHeaders.addAll(tokenHeader.createHeader(jwtToken)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(user)
                .retrieve()
                .toEntity(UserDTO.class);
    }
}
