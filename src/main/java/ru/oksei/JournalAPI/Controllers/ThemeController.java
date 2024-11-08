package ru.oksei.JournalAPI.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.oksei.JournalAPI.DAO.ThemeDAO;
import ru.oksei.JournalAPI.Models.Theme;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/theme")
public class ThemeController {
    @Autowired
    ThemeDAO themeDAO;

    @GetMapping("/{id}")
    public Theme getThemeById(@PathVariable int id) {
        return themeDAO.getThemeById(id);
    }

    @GetMapping("/getAllThemes/{subjectId}")
    public List<Theme> getAllThemesBySubjectId(@PathVariable int subjectId) {
        return themeDAO.getAllThemesBySubjectId(subjectId);
    }

    @PostMapping("/addTheme")
    public void addTheme(@ModelAttribute Theme theme) {
        themeDAO.addTheme(theme);
    }
}
