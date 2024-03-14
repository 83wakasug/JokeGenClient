package com.JokeGenClient.service;

import com.JokeGenClient.client.JokeGenInterface;
import com.JokeGenClient.form.JokesForm;
import com.JokeGenClient.token.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
@Service
@RequiredArgsConstructor
public class GeneralService implements JokeGenInterface {
    private final RestClient restClient;
    private final Token tokenHeader;

    @Override
    public ResponseEntity<?> getJokes(String jwtToken) {
        return null;
    }

    @Override
    public ResponseEntity<?> postJokes(String jwtToken) {
        return restClient.post()
                .uri("/jokes")
                .headers(httpHeaders -> httpHeaders.addAll(tokenHeader.createHeader(jwtToken)))
                .contentType(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(JokesForm.class);
    }

    @Override
    public ResponseEntity<?> getAJokes(int id,String jwtToken) {

        return restClient.get()
                .uri("/jokes/{id}")
                .headers(httpHeaders -> httpHeaders.addAll(tokenHeader.createHeader(jwtToken)))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(JokesForm.class);


    }
}
