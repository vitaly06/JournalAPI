package ru.oksei.JournalAPI.Mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.oksei.JournalAPI.Models.RunJournal;
import ru.oksei.JournalAPI.Models.ThemeJournal;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RunJournalMapper implements RowMapper<RunJournal> {
    @Override
    public RunJournal mapRow(ResultSet resultSet, int i) throws SQLException {
        RunJournal runJournal = new RunJournal();
        try{
            runJournal.setRecordId(resultSet.getInt("recordId"));
            runJournal.setStudentId(resultSet.getInt("studentId"));
            runJournal.setSubjectId(resultSet.getInt("subjectId"));
            runJournal.setOffsetId(resultSet.getInt("offsetId"));
            runJournal.setClassId(resultSet.getInt("classId"));
            runJournal.setEstimation(resultSet.getString("estimation"));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return new RunJournal();
        }
        return runJournal;
    }
}
