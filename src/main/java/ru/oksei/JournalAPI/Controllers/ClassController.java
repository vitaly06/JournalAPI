package ru.oksei.JournalAPI.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.oksei.JournalAPI.Models.Class;
import ru.oksei.JournalAPI.Services.ClassService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/class")
public class ClassController {
    private ClassService classService;
    @Autowired
    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    @GetMapping("/{id}")
    public Optional<Class> getClass(@PathVariable int id) {
        return classService.getClassById(id);
    }

    @GetMapping("/getAllClasses")
    public List<Class> getAllClasses() {
        return classService.getAllClasses();
    }

    @PostMapping("/addClass")
    public void addClass(@ModelAttribute Class c) {
        classService.addClass(c);
    }
}
