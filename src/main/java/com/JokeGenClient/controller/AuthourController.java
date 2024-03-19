package com.JokeGenClient.controller;

import com.JokeGenClient.form.AuthorDTO;
import com.JokeGenClient.form.UserData;
import com.JokeGenClient.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("userData")
@RequestMapping("/jokes")
@RequiredArgsConstructor

public class AuthourController {
    private final AuthorService authorService;
    @GetMapping("/author")
    public String authourListView(Model model, @ModelAttribute("userData") UserData userData,@ModelAttribute @Validated AuthorDTO author) {
        model.addAttribute("author",author);
        model.addAttribute("userdata", userData);
        return "author";
    }

    @GetMapping("author/delete")
    public String deleteAuthor(Model model, @ModelAttribute("userData") UserData userData, AuthorDTO author){

        model.addAttribute("author",author);
        try{
            authorService.deleteAuthor(userData.getToken(), author.getId());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }


        return "redirect:/author";
    }

    @PostMapping("author/edit")
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
