package ru.oksei.JournalAPI.Mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.oksei.JournalAPI.Models.Theme;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ThemeMapper implements RowMapper<Theme> {
    @Override
    public Theme mapRow(ResultSet resultSet, int i) throws SQLException {
        Theme theme = new Theme();
        try{
            theme.setThemeId(resultSet.getInt("themeId"));
            theme.setThemeName(resultSet.getString("themeName"));
            theme.setSubjectId(resultSet.getInt("subjectId"));
            theme.setTimeInterval(resultSet.getString("timeInterval"));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return new Theme();
        }
        return theme;
    }
}
