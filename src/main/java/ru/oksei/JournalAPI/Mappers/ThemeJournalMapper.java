package ru.oksei.JournalAPI.Mappers;

import org.springframework.jdbc.core.RowMapper;
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
            themeJournal.setEstimation1(resultSet.getString("estimation1"));
            themeJournal.setEstimation2(resultSet.getString("estimation2"));
            themeJournal.setEstimation3(resultSet.getString("estimation3"));
            themeJournal.setEstimation4(resultSet.getString("estimation4"));
            themeJournal.setComent1(resultSet.getString("coment1"));
            themeJournal.setComent2(resultSet.getString("coment2"));
            themeJournal.setComent3(resultSet.getString("coment3"));
            themeJournal.setComent4(resultSet.getString("coment4"));
            themeJournal.setTime(resultSet.getString("time"));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return new ThemeJournal();
        }
        return themeJournal;
    }
}
