package ru.oksei.JournalAPI.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.oksei.JournalAPI.DAO.RunJournalDAO;
import ru.oksei.JournalAPI.Models.RunJournal;
import ru.oksei.JournalAPI.Models.StudentTime;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/runJournal")
public class RunJournalController {
    @Autowired
    private RunJournalDAO runJournalDAO;

    @GetMapping("/{offsetId}")
    public List<RunJournal> getRunJournalByOffsetId(@PathVariable("offsetId") int offsetId) {
        List<RunJournal> runJournals = runJournalDAO.getRunJournalByOffsetId(offsetId);
        return runJournals;
    }

    @PostMapping("/addRunJournalRecord/{classId}-{offsetId}")
    public void addRunJournalRecord(@RequestBody List<RunJournal> runJournal,
                                 @PathVariable("classId") int classId, @PathVariable("offsetId") int offsetId) {
        runJournalDAO.addRecordToRunJournal(runJournal, classId, offsetId);
    }

    @PostMapping("/addTimeToStudents/{classId}-{offsetId}")
    public void addTimeToStudents(@RequestBody List<StudentTime> students,
                                  @PathVariable("classId") int classId, @PathVariable("offsetId") int offsetId) {
        runJournalDAO.setEstimationToStudents(students, classId, offsetId);
    }
}
