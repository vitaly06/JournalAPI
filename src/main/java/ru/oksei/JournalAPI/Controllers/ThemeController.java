package ru.oksei.JournalAPI.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.oksei.JournalAPI.Models.Theme;
import ru.oksei.JournalAPI.Services.ThemeService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/theme")
public class ThemeController {
    private final ThemeService themeService;

    @Autowired
    public ThemeController(ThemeService themeService) {
        this.themeService = themeService;
    }

    @GetMapping("/{id}")
    public Optional<Theme> getThemeById(@PathVariable("id") int id) {
        return themeService.getThemeById(id);
    }

    @GetMapping("/getAllThemes/{subjectId}")
    public List<Theme> getAllThemesBySubjectId(@PathVariable int subjectId) {
        return themeService.getAllThemesBySubjectId(subjectId);
    }

    @PostMapping("/addTheme/{subjectId}")
    public void addTheme(@ModelAttribute Theme theme, @PathVariable("subjectId") int subjectId) {
        themeService.addTheme(theme, subjectId);
    }
}
