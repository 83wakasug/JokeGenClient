package com.JokeGenClient.service;

import com.JokeGenClient.client.AdminInterface;
import com.JokeGenClient.form.AuthorDTO;
import com.JokeGenClient.form.AuthorForm;
import com.JokeGenClient.form.UserDTO;
import com.JokeGenClient.token.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService implements AdminInterface {
    private final RestClient restClient;
    private final Token tokenHeader;
    private final String ADMIN="/admin";
    private final String ADMINID="/admin/{id}";


    @Override
    public ResponseEntity<?> updateJokes(String jwtToken) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteJokes(int id,String jwtToken) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteAuthor(int id,String jwtToken) {
        return null;
    }

    @Override
    public ResponseEntity<?> getAUser(String jwtToken) {
        return restClient.get()
                .uri(ADMINID)
                .headers(httpHeaders -> httpHeaders.addAll(tokenHeader.createHeader(jwtToken)))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(UserDTO.class);
    }

    @Override
    public ResponseEntity<List> getAUsers(String jwtToken) {
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
                .uri("/{id}",id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(UserDTO.class);
    }

    @Override
    public ResponseEntity<?> updateAUser(int id,String jwtToken) {
        return null;
    }
}
