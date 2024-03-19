package com.JokeGenClient.service;

import com.JokeGenClient.client.AuthorInterface;
import com.JokeGenClient.form.AuthorDTO;
import com.JokeGenClient.form.AuthorForm;
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
public class AuthorService implements AuthorInterface {

    private final RestClient restClient;
    private final Token tokenHeader;
    private final String AUTHOR="/author";
    @Override
    public ResponseEntity<?> getAuthor(String jwtToken,int id) {
        return restClient.get()
                .uri("/author/{id}",id)
                .headers(httpHeaders -> httpHeaders.addAll(tokenHeader.createHeader(jwtToken)))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(AuthorDTO.class);
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
    public ResponseEntity<?> deleteAuthor(String jwtToken,int id) {

        return restClient.delete()
                .uri("/author/{id}",id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(AuthorDTO.class);
    }

    @Override
    public ResponseEntity<?> updateAuthor(String jwtToken,AuthorDTO author) {

        Map<String, Object> uriVariables = Collections.singletonMap("id", author.getId());
        return restClient.put()
                .uri("/author/{id}",uriVariables)
                .headers(httpHeaders -> httpHeaders.addAll(tokenHeader.createHeader(jwtToken)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(author)
                .retrieve()
                .toEntity(AuthorDTO.class);
    }
}
