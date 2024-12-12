package ru.oksei.JournalAPI.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.oksei.JournalAPI.Models.Class;
import ru.oksei.JournalAPI.Models.Student;
import ru.oksei.JournalAPI.Repositories.ClassRepository;
import ru.oksei.JournalAPI.Repositories.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class StudentService {
    private final ClassRepository classRepository;
    private StudentRepository studentRepository;
    @Autowired
    public StudentService(StudentRepository studentRepository, ClassRepository classRepository) {
        this.studentRepository = studentRepository;
        this.classRepository = classRepository;
    }
    public Optional<Student> getStudentById(int id){
        return studentRepository.findById(id);
    }

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    @Transactional
    public void addStudent(Student student, int classId){
        Class schoolClass = classRepository.findById(classId).get();
        schoolClass.getStudents().add(student);
        student.setSchoolClass(schoolClass);
        studentRepository.save(student);
        classRepository.save(schoolClass);
    }

    public List<Student> getStudentsByClassId(int classId){
        return studentRepository.findAllBySchoolClass_ClassId(classId);
    }
}
