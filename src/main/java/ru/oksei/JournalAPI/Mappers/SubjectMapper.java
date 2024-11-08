package ru.oksei.JournalAPI.Mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.oksei.JournalAPI.Models.SchoolSubject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SubjectMapper implements RowMapper<SchoolSubject> {
    @Override
    public SchoolSubject mapRow(ResultSet resultSet, int i) throws SQLException {
        SchoolSubject subject = new SchoolSubject();
        try{
            subject.setSubjectId(resultSet.getInt("subjectId"));
            subject.setSubjectName(resultSet.getString("subjectName"));
            subject.setClassId(resultSet.getInt("classId"));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return new SchoolSubject();
        }
        return subject;
    }
}
