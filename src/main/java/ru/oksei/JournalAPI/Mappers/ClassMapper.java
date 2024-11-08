package ru.oksei.JournalAPI.Mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.oksei.JournalAPI.Models.Class;
import ru.oksei.JournalAPI.Models.Student;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClassMapper implements RowMapper<Class> {
    @Override
    public Class mapRow(ResultSet resultSet, int i) throws SQLException {
        Class c = new Class();
        try{
            c.setClassId(resultSet.getInt("classId"));
            c.setClassName(resultSet.getString("className"));
            c.setPlanForYear(resultSet.getInt("planForYear"));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return new Class();
        }
        return c;
    }
}
