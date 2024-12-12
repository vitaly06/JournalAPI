package ru.oksei.JournalAPI.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.oksei.JournalAPI.Models.Student;
import ru.oksei.JournalAPI.Services.StudentService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/student")
public class StudentController {
    StudentService studentService;
    @Autowired
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping("/{id}")
    public Optional<Student> getStudentById(@PathVariable("id") int id) {
        return studentService.getStudentById(id);
    }

    @GetMapping("/getAllStudents")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping("/addStudent/{classId}")
    public void addStudent(@ModelAttribute Student student, @PathVariable("classId") int classId) {
        studentService.addStudent(student, classId);
    }

    @GetMapping("/getStudentsByClassId/{classId}")
    public List<Student> getStudentsByClassId(@PathVariable("classId") int classId) {
        return studentService.getStudentsByClassId(classId);
    }
}
