package com.JokeGenClient.form;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;


@Data
@NoArgsConstructor
@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserData {

    @ModelAttribute("userData")
    public UserData createUserData() {
        return new UserData();
    }

    @JsonProperty("jwt")
    String token;

    @JsonProperty("username")
    String username;

    @JsonProperty("authority")
    String roles;


}
