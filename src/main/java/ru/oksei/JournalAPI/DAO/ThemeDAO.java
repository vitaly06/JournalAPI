package ru.oksei.JournalAPI.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.oksei.JournalAPI.Mappers.SubjectMapper;
import ru.oksei.JournalAPI.Mappers.ThemeMapper;
import ru.oksei.JournalAPI.Models.SchoolSubject;
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

    public List<Theme> getAllThemes(){
        assert jdbcTemplate != null;
        return jdbcTemplate.query("SELECT * FROM Themes", new ThemeMapper());
    }

    public void addTheme(Theme theme){
        assert jdbcTemplate != null;
            jdbcTemplate.update("INSERT INTO Themes(themeName, subjectId) VALUES(?, ?) ",
                theme.getThemeName(), theme.getSubjectId());
    }
}
