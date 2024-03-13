package com.JokeGenClient.form;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Component
public class UserData {

    @ModelAttribute("userData")
    public UserData createUserData() {
        return new UserData();
    }

    String token;
    String username;
    String roles;


}
