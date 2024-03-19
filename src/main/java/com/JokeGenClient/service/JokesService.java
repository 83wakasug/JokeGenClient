package com.JokeGenClient.service;

import com.JokeGenClient.client.JokeGenInterface;
import com.JokeGenClient.form.*;
import com.JokeGenClient.token.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class JokesService implements JokeGenInterface {
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
                .uri("/jokes/{id}",id)
                .headers(httpHeaders -> httpHeaders.addAll(tokenHeader.createHeader(jwtToken)))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(JokesDTO.class);

    }

    @Override
    public ResponseEntity<?> updateJokes(String jwtToken, AuthorDTO author) {
          Map<String, Object> uriVariables = Collections.singletonMap("id", author.getId());
        return restClient.put()
                .uri("/jokes/{id}",uriVariables)
                .headers(httpHeaders -> httpHeaders.addAll(tokenHeader.createHeader(jwtToken)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(author)
                .retrieve()
                .toEntity(JokesDTO.class);
    }

    @Override
    public ResponseEntity<?> deleteJokes(int id,String jwtToken) {
        return restClient.delete()
                .uri("/jokes/{id}",id)
                .headers(httpHeaders -> httpHeaders.addAll(tokenHeader.createHeader(jwtToken)))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(JokesForm.class);
    }
}
