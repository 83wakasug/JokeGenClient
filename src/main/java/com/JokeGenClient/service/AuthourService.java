package com.JokeGenClient.service;

import com.JokeGenClient.client.AuthorInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthourService implements AuthorInterface {


    @Override
    public ResponseEntity<?> getAuthor() {
        return null;
    }

    @Override
    public ResponseEntity<?> getAllAuthor() {
        return null;
    }

    @Override
    public ResponseEntity<?> addAuthor() {
        return null;
    }
}
