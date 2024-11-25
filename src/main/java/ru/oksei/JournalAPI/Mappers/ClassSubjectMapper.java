package ru.oksei.JournalAPI.Mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.oksei.JournalAPI.Models.ClassSubject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClassSubjectMapper implements RowMapper<ClassSubject> {
    @Override
    public ClassSubject mapRow(ResultSet resultSet, int i) throws SQLException {
        ClassSubject classSubject = new ClassSubject();
        try{
            classSubject.setClassId(resultSet.getInt("classId"));
            classSubject.setSubjectId(resultSet.getInt("subjectId"));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return new ClassSubject();
        }
        return classSubject;
    }
}
