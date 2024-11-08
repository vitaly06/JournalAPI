package ru.oksei.JournalAPI.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.oksei.JournalAPI.DAO.StudentDAO;
import ru.oksei.JournalAPI.Models.Student;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentDAO studentDAO;
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable int id) {
        return studentDAO.getStudentById(id);
    }

    @GetMapping("/getAllStudents")
    public List<Student> getAllStudents() {
        return studentDAO.getAllStudents();
    }

    @PostMapping("/addStudent")
    public void addStudent(@ModelAttribute Student student) {
        studentDAO.addStudent(student);
    }
}
