package com.JokeGenClient.controller;

import com.JokeGenClient.form.AuthorDTO;
import com.JokeGenClient.form.UserData;
import com.JokeGenClient.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("userData")
@RequestMapping("/jokes")
@RequiredArgsConstructor

public class AuthourController {
    private final AuthorService authorService;
    @GetMapping("/author")
    public String authourListView(Model model, @ModelAttribute("userData") UserData userData) {



        ResponseEntity<List> responseEntity=authorService.getAuthors(userData.getToken());
        ArrayList<AuthorDTO>author = (ArrayList<AuthorDTO>) responseEntity.getBody();
        model.addAttribute("userdata", userData);
        model.addAttribute("author", author);

        return "authorList";

    }

    @GetMapping("/author/edit/{id}")
    public String authorEditView(Model model, @ModelAttribute("userData") UserData userData,@PathVariable int id) {
        try{
            ResponseEntity<?>responseEntity= authorService.getAuthor(userData.getToken(), id);
            AuthorDTO author = (AuthorDTO) responseEntity.getBody();
            model.addAttribute("userdata",userData);
            model.addAttribute("author",author);
        }

        catch (Exception e){
            System.out.println(userData);
            System.out.println(e.getMessage());
        }
        return "editAuthor";
    }

    @GetMapping("/author/delete")
    public String deleteAuthor(Model model, @ModelAttribute("userData") UserData userData, AuthorDTO author){

        model.addAttribute("author",author);
        try{
            authorService.deleteAuthor(userData.getToken(), author.getId());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }


        return "redirect:/author";
    }

    @PostMapping("/author/edit")
    public String authorEdit(Model model,@ModelAttribute("userData") UserData userData,@RequestBody AuthorDTO author){
        model.addAttribute("author",author);

        try{
            authorService.updateAuthor(userData.getToken(),author);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return "redirect:/author";
    }

}
