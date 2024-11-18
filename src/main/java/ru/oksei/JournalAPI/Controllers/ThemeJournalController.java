package ru.oksei.JournalAPI.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.oksei.JournalAPI.DAO.ThemeJournalDAO;
import ru.oksei.JournalAPI.Models.ThemeJournal;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/themeJournal")
public class ThemeJournalController {
    @Autowired
    ThemeJournalDAO themeJournalDAO;

    @GetMapping("/{themeId}")
    public List<ThemeJournal> getJournalByThemeId(@PathVariable int themeId) {
        return themeJournalDAO.getThemeJournalByThemeId(themeId);
    }

    @PostMapping("/addJournalRecord/{classId}-{themeId}")
    public void addJournalRecord(@ModelAttribute List<ThemeJournal> themeJournal,
                                 @PathVariable int classId, @PathVariable int themeId) {
        themeJournalDAO.addRecortToJournal(themeJournal, classId, themeId);
    }
}
