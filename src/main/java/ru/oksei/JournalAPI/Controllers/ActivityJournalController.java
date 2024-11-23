package ru.oksei.JournalAPI.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.oksei.JournalAPI.DAO.ActivityJournalDAO;
import ru.oksei.JournalAPI.Models.ActivityJournal;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/activityJournal")
public class ActivityJournalController {
    @Autowired
    ActivityJournalDAO activityJournalDAO;

    @GetMapping("/{themeId}")
    public List<ActivityJournal> getActivityJournalByThemeId(@PathVariable int themeId) {
        return activityJournalDAO.getActivityJournalByThemeId(themeId);
    }

    @PostMapping("/addActivityJournalRecord/{classId}-{themeId}")
    public void addActivityJournalRecord(@RequestBody List<ActivityJournal> activityJournal,
                                 @PathVariable int classId, @PathVariable int themeId) {
        activityJournalDAO.addRecordToActivityJournal(activityJournal, classId, themeId);
    }
}
