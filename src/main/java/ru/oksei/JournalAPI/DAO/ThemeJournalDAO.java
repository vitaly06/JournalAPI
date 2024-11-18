package ru.oksei.JournalAPI.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.oksei.JournalAPI.Mappers.ThemeJournalMapper;
import ru.oksei.JournalAPI.Models.ThemeJournal;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Component
public class ThemeJournalDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ThemeJournalDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public ThemeJournalDAO(){
        this.jdbcTemplate = null;
    }

    public List<ThemeJournal> getThemeJournalByThemeId(int themeId) {
        return jdbcTemplate.query("SELECT * FROM ThemeJournal WHERE themeId = ?",
                new Object[]{themeId}, new ThemeJournalMapper());
    }

    public void addRecortToJournal(List<ThemeJournal> themeJournal, int classId, int themeId) {
        jdbcTemplate.update("DELETE FROM ThemeJournal WHERE themeId = ? AND classId = ?",
                themeId, classId);
        jdbcTemplate.batchUpdate("INSERT INTO ThemeJournal(studentId, subjectId, themeId, classId, estimation1," +
                        "estimation2, estimation3, estimation4) VALUES(?, ?, ?, ?, ?, ?, ?, ?)",
                new BatchPreparedStatementSetter() {

                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setInt(1, themeJournal.get(i).getStudentId());
                        ps.setInt(2, themeJournal.get(i).getSubjectId());
                        ps.setInt(3, themeJournal.get(i).getThemeId());
                        ps.setInt(4, themeJournal.get(i).getClassId());
                        ps.setInt(5, themeJournal.get(i).getEstimation1());
                        ps.setInt(6, themeJournal.get(i).getEstimation2());
                        ps.setInt(7, themeJournal.get(i).getEstimation3());
                        ps.setInt(8, themeJournal.get(i).getEstimation4());
                        // ps.setString(9, themeJournal.get(i).getDate());
                    }

                    @Override
                    public int getBatchSize() {
                        return themeJournal.size();
                    }
                });
    }
}
