package ca.sheridancollege.suranim.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ca.sheridancollege.suranim.model.Phone;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PhoneController {

    @GetMapping("/")
    public String showCart(HttpSession session, Model model) {
        
        if (session.getAttribute("phoneList") == null) {
            session.setAttribute("phoneList", new ArrayList<Phone>());
        }
        
        model.addAttribute("phone", new Phone());

        model.addAttribute("phoneList", session.getAttribute("phoneList"));
        return "index"; 
    }

    @PostMapping("/insertPhone")
    public String insertPhone(@ModelAttribute Phone phone, HttpSession session, Model model) {
       
        List<Phone> phoneList = (List<Phone>) session.getAttribute("phoneList");

        if (phoneList == null) {
            phoneList = new ArrayList<>();
        }

        phoneList.add(phone);

        session.setAttribute("phoneList", phoneList);

        model.addAttribute("phone", new Phone());

        model.addAttribute("phoneList", phoneList);

        return "index";
    }
}

