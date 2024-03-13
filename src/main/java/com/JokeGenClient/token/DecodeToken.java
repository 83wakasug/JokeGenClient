package com.JokeGenClient.token;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class DecodeToken {
    private final int payload=1;
    public String[] splitToken(String token){
        String[]parts = token.split("\\.");
        return parts;
    }

    private static String decode(String encodedString) {
        return new String(Base64.getUrlDecoder().decode(encodedString));
    }

    public String parseJason(String token,String fieldName){
        String [] parts=splitToken(token);
        JSONObject contents = new JSONObject(decode(parts[payload]));
    return contents.optString(fieldName);
    }


}
