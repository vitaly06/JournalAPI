package ru.oksei.JournalAPI.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.oksei.JournalAPI.Mappers.StudentMapper;
import ru.oksei.JournalAPI.Models.Student;

import java.util.List;

@Component
public class StudentDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StudentDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public StudentDAO(){
        jdbcTemplate = null;
    }

    public Student getStudentById(int id){
        assert jdbcTemplate != null;
        return jdbcTemplate.query("SELECT * FROM Students WHERE studentId = ?", new Object[]{id},
                        new StudentMapper()).stream().findAny().orElse(null);
    }

    public List<Student> getAllStudents(){
        assert jdbcTemplate != null;
        return jdbcTemplate.query("SELECT * FROM Students", new StudentMapper());
    }

    public void addStudent(Student student){
        assert jdbcTemplate != null;
        jdbcTemplate.update("INSERT INTO Students(fullName, dateOfBirth, classId) VALUES(?, ?, ?)",
                student.getFullName(), student.getDateOfBirth(), student.getClassId());
    }
}
