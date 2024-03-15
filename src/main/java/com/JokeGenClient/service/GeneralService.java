package com.JokeGenClient.service;

import com.JokeGenClient.client.JokeGenInterface;
import com.JokeGenClient.form.AddJokesForm;
import com.JokeGenClient.form.AuthorForm;
import com.JokeGenClient.form.JokesDTO;
import com.JokeGenClient.form.JokesForm;
import com.JokeGenClient.token.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GeneralService implements JokeGenInterface {
    private final RestClient restClient;
    private final Token tokenHeader;

    @Override
    public ResponseEntity<List> getJokes(String jwtToken) {
        return restClient.get()
                .uri("/jokes")
                .headers(httpHeaders -> httpHeaders.addAll(tokenHeader.createHeader(jwtToken)))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(List.class);
    }

    @Override
    public ResponseEntity<?> postJokes(String jwtToken, AddJokesForm addJokesForm) {
        return restClient.post()
                .uri("/jokes")
                .headers(httpHeaders -> httpHeaders.addAll(tokenHeader.createHeader(jwtToken)))
                .contentType(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(addJokesForm)
                .retrieve()
                .toEntity(AddJokesForm.class);
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
