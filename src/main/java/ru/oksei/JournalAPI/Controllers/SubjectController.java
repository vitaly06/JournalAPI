package ru.oksei.JournalAPI.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.oksei.JournalAPI.Models.SchoolSubject;
import ru.oksei.JournalAPI.Services.SchoolSubjectService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/subject")
public class SubjectController {
    SchoolSubjectService subjectService;
    @Autowired
    public SubjectController(SchoolSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("/{id}")
    public Optional<SchoolSubject> getSubjectById(@PathVariable int id) {
        return subjectService.getSubjectById(id);
    }

    @GetMapping("/getAllSubjects")
    public List<SchoolSubject> getAllSubjects() {
        return subjectService.getAllSubjects();
    }

    @PostMapping("/addSubject/{classId}")
    public void addSubject(@ModelAttribute SchoolSubject subject, @PathVariable("classId") int classId) {
        subjectService.addSubject(subject, classId);
    }

    @GetMapping("/getSubjects/{id}")
    public List<SchoolSubject> getSubjectsByClassId(@PathVariable int id) {
        return subjectService.getSubjectsByClassId(id);
    }
}
