package ru.oksei.JournalAPI.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.oksei.JournalAPI.Mappers.SubjectMapper;
import ru.oksei.JournalAPI.Models.SchoolSubject;

import java.util.List;

@Component
public class SubjectDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SubjectDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public SubjectDAO(){
        jdbcTemplate = null;
    }

    public SchoolSubject getSubjectById(int id){
        assert jdbcTemplate != null;
        return jdbcTemplate.query("SELECT * FROM SchoolSubjects WHERE subjectId = ?", new Object[]{id},
                new SubjectMapper()).stream().findAny().orElse(null);
    }

    public List<SchoolSubject> getAllSubjects(){
        assert jdbcTemplate != null;
        return jdbcTemplate.query("SELECT * FROM SchoolSubjects", new SubjectMapper());
    }

    public void addSubject(SchoolSubject subject){
        assert jdbcTemplate != null;
        jdbcTemplate.update("INSERT INTO SchoolSubjects(subjectName, classId) VALUES(?, ?) ",
                subject.getSubjectName(), subject.getClassId());
    }
}
