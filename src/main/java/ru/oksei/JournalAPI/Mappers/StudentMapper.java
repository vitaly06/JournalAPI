package ru.oksei.JournalAPI.Mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.oksei.JournalAPI.Models.Student;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentMapper implements RowMapper<Student> {
    @Override
    public Student mapRow(ResultSet resultSet, int i) throws SQLException {
        Student student = new Student();
        try{
            student.setStudentId(resultSet.getInt("studentId"));
            student.setFullName(resultSet.getString("fullName"));
            student.setDateOfBirth(resultSet.getString("dateOdfBirth"));
            student.setClassId(resultSet.getInt("classId"));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return new Student();
        }
        return student;
    }
}
