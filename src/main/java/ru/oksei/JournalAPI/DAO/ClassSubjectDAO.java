package ru.oksei.JournalAPI.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.oksei.JournalAPI.Mappers.SubjectMapper;
import ru.oksei.JournalAPI.Models.SchoolSubject;

import java.util.List;

@Component
public class ClassSubjectDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ClassSubjectDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public ClassSubjectDAO(){
        jdbcTemplate = null;
    }

    public List<SchoolSubject> getSubjectsById(int id){
        return jdbcTemplate.query("SELECT ss.subjectId, ss.subjectName FROM SchoolSubjects as ss INNER JOIN ClassSubject as cs\n" +
                "WHERE cs.classId = ? AND ss.subjectId = cs.subjectId", new Object[]{id}, new SubjectMapper());
    }
}
