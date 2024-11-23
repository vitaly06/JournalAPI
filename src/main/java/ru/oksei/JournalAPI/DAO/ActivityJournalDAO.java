package ru.oksei.JournalAPI.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.oksei.JournalAPI.Mappers.ActivityJournalMapper;
import ru.oksei.JournalAPI.Models.ActivityJournal;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Component
public class ActivityJournalDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public ActivityJournalDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public ActivityJournalDAO(){
        this.jdbcTemplate = null;
    }

    public List<ActivityJournal> getActivityJournalByThemeId(int themeId) {
        return jdbcTemplate.query("SELECT * FROM ActivityJournal WHERE themeId = ?",
                new Object[]{themeId}, new ActivityJournalMapper());
    }

    public void addRecordToActivityJournal(List<ActivityJournal> activityJournal, int classId, int themeId) {
        jdbcTemplate.update("DELETE FROM AcitivtyJournal WHERE themeId = ? AND classId = ?",
                themeId, classId);
        jdbcTemplate.batchUpdate("INSERT INTO ActivityJournal(studentId, subjectId, themeId, classId, theme1," +
                        "theme2, theme3, theme4, activity1, activity2, activity3, activity4) " +
                        "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                new BatchPreparedStatementSetter() {

                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setInt(1, activityJournal.get(i).getStudentId());
                        ps.setInt(2, activityJournal.get(i).getSubjectId());
                        ps.setInt(3, activityJournal.get(i).getThemeId());
                        ps.setInt(4, activityJournal.get(i).getClassId());
                        ps.setString(5, activityJournal.get(i).getTheme1());
                        ps.setString(6, activityJournal.get(i).getTheme2());
                        ps.setString(7, activityJournal.get(i).getTheme3());
                        ps.setString(8, activityJournal.get(i).getTheme3());
                        ps.setString(9, activityJournal.get(i).getActivity1());
                        ps.setString(10, activityJournal.get(i).getActivity2());
                        ps.setString(11, activityJournal.get(i).getActivity3());
                        ps.setString(12, activityJournal.get(i).getActivity4());
                    }

                    @Override
                    public int getBatchSize() {
                        return activityJournal.size();
                    }
                });
    }
}
