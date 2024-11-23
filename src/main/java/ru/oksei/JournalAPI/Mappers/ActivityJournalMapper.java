package ru.oksei.JournalAPI.Mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.oksei.JournalAPI.Models.ActivityJournal;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ActivityJournalMapper implements RowMapper<ActivityJournal> {

    @Override
    public ActivityJournal mapRow(ResultSet rs, int rowNum) throws SQLException {
        ActivityJournal aj = new ActivityJournal();
        aj.setRecordId(rs.getInt("recordId"));
        aj.setStudentId(rs.getInt("studentId"));
        aj.setSubjectId(rs.getInt("subjectId"));
        aj.setThemeId(rs.getInt("themeId"));
        aj.setClassId(rs.getInt("classId"));
        aj.setTheme1(rs.getString("theme1"));
        aj.setTheme2(rs.getString("theme2"));
        aj.setTheme3(rs.getString("theme3"));
        aj.setTheme4(rs.getString("theme4"));
        aj.setActivity1(rs.getString("activity1"));
        aj.setActivity2(rs.getString("activity2"));
        aj.setActivity3(rs.getString("activity3"));
        aj.setActivity4(rs.getString("activity4"));
        return aj;
    }
}
