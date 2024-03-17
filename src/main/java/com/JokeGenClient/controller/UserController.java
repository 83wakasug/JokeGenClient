package com.JokeGenClient.controller;

import com.JokeGenClient.form.JokesDTO;
import com.JokeGenClient.form.UserDTO;
import com.JokeGenClient.form.UserData;
import com.JokeGenClient.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@SessionAttributes("userData")
@RequestMapping("/jokes")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public String User(Model model, @ModelAttribute("userData") UserData userData) {

        try {
            ResponseEntity<?> responseEntity = userService.getAUsers(userData.getToken());
            ArrayList<UserDTO> users = (ArrayList<UserDTO>) responseEntity.getBody();
            model.addAttribute("users", users);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return "userDetails";
    }

    @DeleteMapping("/user/delete/{id}")
    public String deleteUser(Model model, @ModelAttribute("userData") UserData userData,UserDTO user) {

        try {
            model.addAttribute("user", user);
            userService.deleteAUser(user.getUserId(),userData.getToken());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return "userDetails";
    }

    @PutMapping("/user/update/{id}")
    public String updateUser(Model model, @ModelAttribute("userData") UserData userData,UserDTO user) {

        try {
            model.addAttribute("user", user);
            userService.updateAUser(user,userData.getToken());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return "userDetails";
    }

}
