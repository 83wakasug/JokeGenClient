package com.JokeGenClient.controller;

import com.JokeGenClient.form.*;
import com.JokeGenClient.service.AuthorService;
import com.JokeGenClient.service.JokesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Controller
@SessionAttributes("userData")
@RequestMapping("/jokes")
@RequiredArgsConstructor
public class JokesController {

    private final JokesService generalService;
    private final AuthorService authorService;

    @GetMapping("/index")
    public String index(@ModelAttribute("userData") UserData userData) {
        return "index";
    }

    @GetMapping("/list")
    public String jokesList(Model model, @ModelAttribute("userData") UserData userData, JokesDTO jokesDTO) {

        try {
            ResponseEntity<?> responseEntity = generalService.getJokes(userData.getToken());
            ArrayList<JokesDTO> jokesList = (ArrayList<JokesDTO>) responseEntity.getBody();
            model.addAttribute("jokes", jokesList);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return "listJokes";
    }

    @GetMapping("/create")
    public String getAJoke(Model model, @ModelAttribute("userData") UserData userData, JokesForm jokesForm) {
        try {
            model.addAttribute("joke", jokesForm);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "addJokes";
    }


    @PostMapping("/create")
    public String addJoke(@ModelAttribute("userData") UserData userData, Model model, @ModelAttribute @Validated JokesForm jokesForm) {
        try {

            model.addAttribute("joke", jokesForm);
            AuthorDTO author = findAuthoer(jokesForm.getAuthor(), userData);


            int authorid;
            if (author.getName()==null) {
                authorid = addAuthor(jokesForm, userData);
            }
             else{authorid = author.getId();}
            author.setId(authorid);
            generalService.postJokes(userData.getToken(), createAddJokesForm(jokesForm,authorid));


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "addJokes";
    }


    private AuthorDTO findAuthoer(String name, UserData userdata) {
        try {
            ResponseEntity<List> responseEntity = authorService.getAuthors(userdata.getToken());
            List<AuthorDTO> authorsList = new ArrayList<>();
            List<?> responseBody = responseEntity.getBody();
            //ArrayList<AuthorDTO> authorForms = (ArrayList<AuthorDTO>) responseEntity.getBody();
            for (Object obj : responseBody) {
                if (obj instanceof LinkedHashMap) {
                    LinkedHashMap<?, ?> map = (LinkedHashMap<?, ?>) obj;
                    AuthorDTO author = new AuthorDTO();
                    author.setId((Integer) map.get("id"));
                    author.setName((String) map.get("name"));
                    authorsList.add(author);
                }
            }
            for (AuthorDTO author : authorsList) {
                if (author.getName().equals(name)) {
                    return author;
                }
            }
        } catch (Exception e) {
            // Handle the exception, log it, or perform any necessary actions
            System.out.println("An error occurred while finding the author: " + e.getMessage());

        }
        return new AuthorDTO();
    }

    private int addAuthor(JokesForm jokesForm, UserData userdata) {
        AuthorForm authorForm = new AuthorForm();
        authorForm.setName(jokesForm.getAuthor());
        ResponseEntity<?> responseEntity = authorService.addAuthor(userdata.getToken(), authorForm);
        AuthorDTO authorDTO = (AuthorDTO) responseEntity.getBody();
        return authorDTO.getId();
    }

    private AddJokesForm createAddJokesForm(JokesForm jokesForm,int authorid) {
        return new AddJokesForm(jokesForm.getJoke(), authorid);
    }

}
