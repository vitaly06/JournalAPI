package ru.oksei.JournalAPI.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.oksei.JournalAPI.Mappers.ThemeMapper;
import ru.oksei.JournalAPI.Models.Theme;

import java.util.List;

@Component
public class ThemeDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ThemeDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public ThemeDAO(){
        jdbcTemplate = null;
    }

    public Theme getThemeById(int id){
        assert jdbcTemplate != null;
        return jdbcTemplate.query("SELECT * FROM Themes WHERE themeId = ?", new Object[]{id},
                new ThemeMapper()).stream().findAny().orElse(null);
    }

    public List<Theme> getAllThemesBySubjectId(int subjectId){
        assert jdbcTemplate != null;
        return jdbcTemplate.query("SELECT * FROM Themes WHERE subjectId = ?", new Object[]{subjectId}, new ThemeMapper());
    }

    public void addTheme(Theme theme){
        assert jdbcTemplate != null;
            jdbcTemplate.update("INSERT INTO Themes(themeName, subjectId, timeInterval) VALUES(?, ?, ?)",
                theme.getThemeName(), theme.getSubjectId(), theme.getTimeInterval());
    }
}
