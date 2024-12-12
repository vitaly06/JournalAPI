package ru.oksei.JournalAPI.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.oksei.JournalAPI.Models.ActivityJournal;
import ru.oksei.JournalAPI.Requests.ActivityJournalRequest;
import ru.oksei.JournalAPI.Services.ActivityJournalService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/activityJournal")
public class ActivityJournalController {
    private final ActivityJournalService activityJournalService;
    @Autowired
    public ActivityJournalController(ActivityJournalService activityJournalService) {
        this.activityJournalService = activityJournalService;
    }


    @GetMapping("/{themeId}")
    public List<ActivityJournal> getActivityJournalByThemeId(@PathVariable int themeId) {
        return activityJournalService.getActivityJournalByThemeId(themeId);
    }

    @PostMapping("/addActivityJournalRecord/{classId}-{themeId}")
    public void addActivityJournalRecord(@RequestBody List<ActivityJournalRequest> activityJournal,
                                 @PathVariable int classId, @PathVariable int themeId) {
        activityJournalService.addRecordToActivityJournal(activityJournal, classId, themeId);
    }
}
