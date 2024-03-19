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
    public String authourListView(Model model, @ModelAttribute @Validated AuthorDTO author) {
        model.addAttribute("author",author);

        return "author";
    }

    @GetMapping("/delete")
    public String deleteAuthor(Model model, @ModelAttribute("userData") UserData userData, AuthorDTO author){

        model.addAttribute("author",author);

        return "redirect:/author";
    }

    @PostMapping("/edit")
    public String authorEdit(Model model,@ModelAttribute("userData") UserData userData){

        return "redirect:/author";
    }

}
