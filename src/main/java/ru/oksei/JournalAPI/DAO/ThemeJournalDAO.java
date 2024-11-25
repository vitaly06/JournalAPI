package ru.oksei.JournalAPI.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.oksei.JournalAPI.Mappers.ThemeJournalMapper;
import ru.oksei.JournalAPI.Models.StudentTime;
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

    public void setTimeToStudents(List<StudentTime> students, int classId, int themeId){
        jdbcTemplate.batchUpdate("UPDATE ThemeJournal SET time = ? WHERE studentId = ?, classId = ?, themeId = ?",
                new BatchPreparedStatementSetter() {

                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setString(1, students.get(i).getTime());
                        ps.setInt(2, students.get(i).getStudentId());
                        ps.setInt(3, classId);
                        ps.setInt(4, themeId);
                    }

                    @Override
                    public int getBatchSize() {
                        return students.size();
                    }
                });
    }

    public void addRecortToJournal(List<ThemeJournal> themeJournal, int classId, int themeId) {
        jdbcTemplate.update("DELETE FROM ThemeJournal WHERE themeId = ? AND classId = ?",
                themeId, classId);
        jdbcTemplate.batchUpdate("INSERT INTO ThemeJournal(studentId, subjectId, themeId, classId, estimation1," +
                        "estimation2, estimation3, estimation4, coment1, coment2, coment3, coment4) " +
                        "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                new BatchPreparedStatementSetter() {

                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setInt(1, themeJournal.get(i).getStudentId());
                        ps.setInt(2, themeJournal.get(i).getSubjectId());
                        ps.setInt(3, themeJournal.get(i).getThemeId());
                        ps.setInt(4, themeJournal.get(i).getClassId());
                        ps.setString(5, themeJournal.get(i).getEstimation1());
                        ps.setString(6, themeJournal.get(i).getEstimation2());
                        ps.setString(7, themeJournal.get(i).getEstimation3());
                        ps.setString(8, themeJournal.get(i).getEstimation4());
                        ps.setString(9, themeJournal.get(i).getComent1());
                        ps.setString(10, themeJournal.get(i).getComent2());
                        ps.setString(11, themeJournal.get(i).getComent3());
                        ps.setString(12, themeJournal.get(i).getComent4());
                    }

                    @Override
                    public int getBatchSize() {
                        return themeJournal.size();
                    }
                });
    }
}
