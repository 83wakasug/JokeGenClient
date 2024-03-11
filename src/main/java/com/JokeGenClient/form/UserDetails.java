package com.JokeGenClient.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetails {
    int userId;
    String username;
    ArrayList<String> authorities;

}
