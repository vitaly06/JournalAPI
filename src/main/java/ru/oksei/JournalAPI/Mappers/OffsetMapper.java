package ru.oksei.JournalAPI.Mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.oksei.JournalAPI.Models.ClassSubject;
import ru.oksei.JournalAPI.Models.Offset;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OffsetMapper implements RowMapper<Offset> {
    @Override
    public Offset mapRow(ResultSet resultSet, int i) throws SQLException {
        Offset offset = new Offset();
        try{
            offset.setOffsetId(resultSet.getInt("offsetId"));
            offset.setOffsetName(resultSet.getString("offsetName"));
            offset.setSubjectId(resultSet.getInt("subjectId"));
            offset.setDistance(resultSet.getString("distance"));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return new Offset();
        }
        return offset;
    }
}
