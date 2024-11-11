package ru.oksei.JournalAPI.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.oksei.JournalAPI.DAO.ClassSubjectDAO;
import ru.oksei.JournalAPI.Models.SchoolSubject;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/classSubject")
public class ClassSubjectController {
    @Autowired
    ClassSubjectDAO classSubjectDAO;

    @GetMapping("/getSubjects/{id}")
    public List<SchoolSubject> getSubjectsByClassId(@PathVariable int id) {
        return classSubjectDAO.getSubjectsById(id);
    }
}
