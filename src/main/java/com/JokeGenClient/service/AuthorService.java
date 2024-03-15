package com.JokeGenClient.service;

import com.JokeGenClient.client.AuthorInterface;
import com.JokeGenClient.form.AuthorDTO;
import com.JokeGenClient.form.AuthorForm;
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
public class AuthorService implements AuthorInterface {

    private final RestClient restClient;
    private final Token tokenHeader;
    private final String AUTHOR="/author";
    @Override
    public ResponseEntity<?> getAuthor(String jwtToken) {
        return restClient.get()
                .uri("/author/{id}")
                .headers(httpHeaders -> httpHeaders.addAll(tokenHeader.createHeader(jwtToken)))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(AuthorForm.class);
    }

    @Override
    public ResponseEntity<List> getAuthors(String jwtToken) {

            return restClient.get()
                    .uri(AUTHOR)
                    .headers(httpHeaders -> httpHeaders.addAll(tokenHeader.createHeader(jwtToken)))
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .toEntity(List.class);

    }


    @Override
    public ResponseEntity<?> addAuthor(String jwtToken, AuthorForm authorForm) {
         return restClient.post()
                .uri(AUTHOR)
                .headers(httpHeaders -> httpHeaders.addAll(tokenHeader.createHeader(jwtToken)))
                .accept(MediaType.APPLICATION_JSON)
                 .contentType(MediaType.APPLICATION_JSON)
                 .body(authorForm)
                .retrieve()
                .toEntity(AuthorDTO.class);
    }

    @Override
    public ResponseEntity<?> deleteAuthor(String jwtToken) {
        return null;
    }

    @Override
    public ResponseEntity<?> updateAuthor(String jwtToken) {
        return null;
    }
}
