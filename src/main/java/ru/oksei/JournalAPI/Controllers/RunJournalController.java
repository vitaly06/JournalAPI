package ru.oksei.JournalAPI.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.oksei.JournalAPI.Models.RunJournal;
import ru.oksei.JournalAPI.Models.StudentTime;
import ru.oksei.JournalAPI.Services.RunJournalService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/runJournal")
public class RunJournalController {
    private final RunJournalService runJournalService;
    @Autowired
    public RunJournalController(RunJournalService runJournalService) {
        this.runJournalService = runJournalService;
    }

    @GetMapping("/{offsetId}")
    public List<RunJournal> getRunJournalByOffsetId(@PathVariable("offsetId") int offsetId) {
        List<RunJournal> runJournals = runJournalService.getRunJournalByOffsetId(offsetId);
        return runJournals;
    }

    /*@PostMapping("/addRunJournalRecord/{classId}-{offsetId}-{subjectId}")
    public void addRunJournalRecord(@RequestBody List<RunJournalRequest> runJournal,
                                 @PathVariable("classId") int classId, @PathVariable("offsetId") int offsetId,
                                    @PathVariable("subjectId") int subjectId) {
        runJournalService.addRecordToRunJournal(runJournal, classId, offsetId, subjectId);
    }*/

    @PostMapping("/addTimeToStudents/{classId}-{offsetId}-{subjectId}")
    public void addTimeToStudents(@RequestBody List<StudentTime> students,
                                  @PathVariable("classId") int classId, @PathVariable("offsetId") int offsetId,
                                  @PathVariable("subjectId") int subjectId) {
        runJournalService.setEstimationToStudents(students, classId, offsetId, subjectId);
    }
}
