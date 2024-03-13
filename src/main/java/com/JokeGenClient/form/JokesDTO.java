package com.JokeGenClient.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JokesDTO {
    int id;
    String joke;
    AuthourForm authour;
}
