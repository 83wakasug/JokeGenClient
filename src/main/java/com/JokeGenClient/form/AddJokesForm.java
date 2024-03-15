package com.JokeGenClient.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.StringBufferInputStream;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddJokesForm {

    String joke;
    int authorId;

}
