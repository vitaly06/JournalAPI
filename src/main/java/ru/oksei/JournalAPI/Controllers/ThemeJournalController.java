package ru.oksei.JournalAPI.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.oksei.JournalAPI.DAO.ThemeJournalDAO;
import ru.oksei.JournalAPI.Models.Student;
import ru.oksei.JournalAPI.Models.StudentTime;
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
    public void addJournalRecord(@RequestBody List<ThemeJournal> themeJournal,
                                 @PathVariable int classId, @PathVariable int themeId) {
        themeJournalDAO.addRecortToJournal(themeJournal, classId, themeId);
    }

    @PostMapping("/addTimeToStudents/{classId}-{themeId}")
    public void addTimeToStudents(@RequestBody List<StudentTime> students,
            @PathVariable("classId") int classId, @PathVariable("themeId") int themeId) {
        themeJournalDAO.setTimeToStudents(students, classId, themeId);
    }
}
