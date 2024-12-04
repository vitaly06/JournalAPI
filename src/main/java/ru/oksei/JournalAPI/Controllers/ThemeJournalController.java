package ru.oksei.JournalAPI.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.oksei.JournalAPI.DAO.ThemeJournalDAO;
import ru.oksei.JournalAPI.Models.StudentTime;
import ru.oksei.JournalAPI.Models.ThemeJournal;

import java.time.Duration;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/themeJournal")
public class ThemeJournalController {
    @Autowired
    ThemeJournalDAO themeJournalDAO;

    @GetMapping("/{themeId}")
    public List<ThemeJournal> getJournalByThemeId(@PathVariable int themeId) {
        long milliseconds;
        List<ThemeJournal> themeJournals = themeJournalDAO.getThemeJournalByThemeId(themeId);
        for (ThemeJournal themeJournal : themeJournals) {
            if (themeJournal.getTime() != null) {
                try {
                    milliseconds = Long.parseLong(themeJournal.getTime());
                    // Преобразуем миллисекунды в Duration
                    Duration duration = Duration.ofMillis(milliseconds);
                    // Получаем часы, минуты и секунды
                    long hours = duration.toHours();
                    long minutes = duration.toMinutes() % 60; // Остаток минут
                    long seconds = duration.getSeconds() % 60; // Остаток секунд

                    // Форматируем строку
                    String timeFormatted = String.format("%02d:%02d:%02d", hours, minutes, seconds);
                    themeJournal.setTime(timeFormatted);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return themeJournals;
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

