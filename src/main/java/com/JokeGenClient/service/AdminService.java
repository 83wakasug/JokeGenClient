package com.JokeGenClient.service;

import com.JokeGenClient.client.AdminInterface;
import com.JokeGenClient.token.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class AdminService implements AdminInterface {
    private final RestClient restClient;
    private final Token tokenHeader;


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
        return null;
    }

    @Override
    public ResponseEntity<?> getAUsers(String jwtToken) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteAUser(int id,String jwtToken) {
        return null;
    }

    @Override
    public ResponseEntity<?> updateAUser(int id,String jwtToken) {
        return null;
    }
}
