package ru.oksei.JournalAPI.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.oksei.JournalAPI.DAO.OffsetDAO;
import ru.oksei.JournalAPI.Models.Offset;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/offset")
public class OffsetController {
    @Autowired
    private OffsetDAO offsetDAO;

    @GetMapping("/{id}")
    public Offset getOffsetById(@PathVariable int id) {
        return offsetDAO.getOffsetById(id);
    }

    @GetMapping("/getAllOffsets/{subjectId}")
    public List<Offset> getAllOffsetsBySubjectId(@PathVariable int subjectId) {
        return offsetDAO.getAllOffsetsBySubjectId(subjectId);
    }

    @PostMapping("/addOffset")
    public void addOffset(@ModelAttribute Offset offset) {
        offsetDAO.addOffset(offset);
    }
}
