package ca.sheridancollege.suranim.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
  
   
    @GetMapping("/")
    public String index() {
    	return "index";
    }
    @GetMapping("favoriteColour")
    public String favoriteColour() {
    	return "favoriteColour";
    }
    @GetMapping("/height")
    public String height() {
        return "height";
    }

    @GetMapping("/funFact")
    public String funFact() {
        return "funFact";
    }
}
