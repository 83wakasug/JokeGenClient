package com.JokeGenClient.controller;

import com.JokeGenClient.form.JokesDTO;
import com.JokeGenClient.form.UserDTO;
import com.JokeGenClient.form.UserData;
import com.JokeGenClient.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("userData")
@RequestMapping("/jokes/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("")
    public String UserListView(Model model, @ModelAttribute("userData") UserData userData) {

        try {
            ResponseEntity<?> responseEntity = userService.getUsers(userData.getToken());
            List<UserDTO> users = (List<UserDTO>) responseEntity.getBody();
            model.addAttribute("users", users);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return "userDetails";
    }

    @GetMapping("/edit/{id}")
    public String userEditView(Model model, @ModelAttribute("userData") UserData userData, @PathVariable int id, @ModelAttribute @Validated UserDTO user, BindingResult bindingResult) {
        System.out.println(id + "id");
        try {
            ResponseEntity<?> responseEntity = userService.getAUser(userData.getToken(), id);
            user = (UserDTO) responseEntity.getBody();
            System.out.println(user);
            model.addAttribute("user", user);
            model.addAttribute("userData",userData);
            model.addAttribute("userId", id);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        return "editUser";
    }



    @DeleteMapping("/delete/{id}")
    public String deleteUser(Model model, @ModelAttribute("userData") UserData userData,@ModelAttribute @Validated UserDTO user) {

        try {
            model.addAttribute("user", user);
            userService.deleteAUser(user.getUserId(),userData.getToken());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return "userDetails";
    }

    @PostMapping("/edit")
    public String updateUser(Model model, @ModelAttribute("userData") UserData userData,@ModelAttribute @Validated UserDTO user,BindingResult bindingResult) {

        try {
            model.addAttribute("user", user);
            System.out.println(user.getUserId() );
            System.out.println( user.getAuthorities() + user.getUsername());
            userService.updateAUser(user,userData.getToken());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return "redirect:/jokes/users";
    }

}
