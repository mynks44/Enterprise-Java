package ca.sheridancollege.suranim.controller;

import ca.sheridancollege.suranim.beans.Student;
import ca.sheridancollege.suranim.database.DatabaseAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StudentController {

    @Autowired
    private DatabaseAccess databaseAccess;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("students", databaseAccess.findAll());
        return "index";
    }

    @PostMapping("/insert")
    public String insertStudent(@RequestParam String name, @RequestParam String course) {
        Student student = new Student();
        student.setName(name);
        student.setCourse(course);
        databaseAccess.save(student);
        return "redirect:/";
    }
}