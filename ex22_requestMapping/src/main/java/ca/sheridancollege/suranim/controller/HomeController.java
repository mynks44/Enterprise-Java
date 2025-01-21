package ca.sheridancollege.suranim.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String index() {
        return "index";
    }

    @PostMapping("/formPost")
    public String handleFormSubmission(
        @RequestParam String bookName,
        @RequestParam String authorName,
        @RequestParam String publicationDate
    ) {
        System.out.println("Book Name: " + bookName);
        System.out.println("Author Name: " + authorName);
        System.out.println("Publication Date: " + publicationDate);
        return "working"; 
    }
}
