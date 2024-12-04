package ru.oksei.JournalAPI.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.oksei.JournalAPI.Mappers.OffsetMapper;
import ru.oksei.JournalAPI.Models.Offset;

import java.util.List;

@Component
public class OffsetDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public OffsetDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Offset getOffsetById(int id){
        assert jdbcTemplate != null;
        return jdbcTemplate.query("SELECT * FROM Offset WHERE offsetId = ?", new Object[]{id},
                new OffsetMapper()).stream().findAny().orElse(null);
    }

    public List<Offset> getAllOffsetsBySubjectId(int subjectId){
        assert jdbcTemplate != null;
        return jdbcTemplate.query("SELECT * FROM Offset WHERE subjectId = ?", new Object[]{subjectId},
                new OffsetMapper());
    }

    public void addOffset(Offset offset){
        assert jdbcTemplate != null;
        jdbcTemplate.update("INSERT INTO Offset(offsetName, subjectId, distance) VALUES(?, ?, ?)",
                offset.getOffsetName(), offset.getSubjectId(), offset.getDistance());
    }
}
