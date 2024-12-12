package ru.oksei.JournalAPI.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.oksei.JournalAPI.Models.Offset;
import ru.oksei.JournalAPI.Services.OffsetService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/offset")
public class OffsetController {
    private final OffsetService offsetService;
    @Autowired
    public OffsetController(OffsetService offsetService) {
        this.offsetService = offsetService;
    }

    @GetMapping("/{id}")
    public Offset getOffsetById(@PathVariable int id) {
        return offsetService.getOffsetById(id);
    }

    @GetMapping("/getAllOffsets/{subjectId}")
    public List<Offset> getAllOffsetsBySubjectId(@PathVariable int subjectId) {
        return offsetService.getAllOffsetsBySubjectId(subjectId);
    }

    @PostMapping("/addOffset/{subjectId}")
    public void addOffset(@ModelAttribute Offset offset, @PathVariable("subjectId") int subjectId) {
        offsetService.addOffset(offset, subjectId);
    }
}
