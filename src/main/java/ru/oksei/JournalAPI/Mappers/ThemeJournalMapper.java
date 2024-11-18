package ru.oksei.JournalAPI.Mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.oksei.JournalAPI.Models.SchoolSubject;
import ru.oksei.JournalAPI.Models.ThemeJournal;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ThemeJournalMapper implements RowMapper<ThemeJournal> {
    @Override
    public ThemeJournal mapRow(ResultSet resultSet, int i) throws SQLException {
        ThemeJournal themeJournal = new ThemeJournal();
        try{
            themeJournal.setRecordId(resultSet.getInt("recordId"));
            themeJournal.setStudentId(resultSet.getInt("studentId"));
            themeJournal.setSubjectId(resultSet.getInt("subjectId"));
            themeJournal.setThemeId(resultSet.getInt("themeId"));
            themeJournal.setClassId(resultSet.getInt("classId"));
            themeJournal.setEstimation1(resultSet.getInt("estimation1"));
            themeJournal.setEstimation2(resultSet.getInt("estimation2"));
            themeJournal.setEstimation3(resultSet.getInt("estimation3"));
            themeJournal.setEstimation4(resultSet.getInt("estimation4"));
            themeJournal.setDate(resultSet.getString("date"));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return new ThemeJournal();
        }
        return themeJournal;
    }
}
