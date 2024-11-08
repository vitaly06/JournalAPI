package ru.oksei.JournalAPI.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.oksei.JournalAPI.DAO.SubjectDAO;
import ru.oksei.JournalAPI.Models.SchoolSubject;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/subject")
public class SubjectController {
    @Autowired
    SubjectDAO subjectDAO;

    @GetMapping("/{id}")
    public SchoolSubject getSubjectById(@PathVariable int id) {
        return subjectDAO.getSubjectById(id);
    }

    @GetMapping("/getAllSubjects")
    public List<SchoolSubject> getAllSubjects() {
        return subjectDAO.getAllSubjects();
    }

    @PostMapping("/addSubject")
    public void addSubject(@ModelAttribute SchoolSubject subject) {
        subjectDAO.addSubject(subject);
    }
}
