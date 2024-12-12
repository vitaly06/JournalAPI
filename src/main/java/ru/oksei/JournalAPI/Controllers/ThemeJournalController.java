package ru.oksei.JournalAPI.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.oksei.JournalAPI.Models.ThemeJournal;
import ru.oksei.JournalAPI.Requests.ThemeJournalRequest;
import ru.oksei.JournalAPI.Services.ThemeJournalService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/themeJournal")
public class ThemeJournalController {
    private ThemeJournalService themeJournalService;
    @Autowired
    public ThemeJournalController(ThemeJournalService themeJournalService) {
        this.themeJournalService = themeJournalService;
    }

    @GetMapping("/{themeId}")
    public List<ThemeJournal> getJournalByThemeId(@PathVariable int themeId) {
        return themeJournalService.getThemeJournalByThemeId(themeId);
    }

    @PostMapping("/addJournalRecord/{classId}-{themeId}")
    public void addJournalRecord(@RequestBody List<ThemeJournalRequest> themeJournal,
                                 @PathVariable int classId, @PathVariable int themeId) {
        themeJournalService.addThemeJournal(themeJournal, classId, themeId);
    }

}

