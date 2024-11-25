package ru.oksei.JournalAPI.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.oksei.JournalAPI.DAO.ClassDAO;
import ru.oksei.JournalAPI.Models.Class;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/class")
public class ClassController {
    @Autowired
    ClassDAO classDAO;

    @GetMapping("/{id}")
    public Class getClass(@PathVariable int id) {
        return classDAO.getClassById(id);
    }

    @GetMapping("/getAllClasses")
    public List<Class> getAllClasses() {
        return classDAO.getAllClasses();
    }

    @PostMapping("/addClass")
    public void addClass(@ModelAttribute Class c) {
        classDAO.addClass(c);
    }
}
