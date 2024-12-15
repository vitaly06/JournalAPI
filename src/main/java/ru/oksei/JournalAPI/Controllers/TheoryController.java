package ru.oksei.JournalAPI.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.oksei.JournalAPI.Models.Theory;
import ru.oksei.JournalAPI.Services.TheoryService;

@RestController
@CrossOrigin
@RequestMapping("/theory")
public class TheoryController {
    private final TheoryService theoryService;
    @Autowired
    public TheoryController(TheoryService theoryService) {
        this.theoryService = theoryService;
    }

    @PostMapping("/addTheory/{themeId}")
    public void addTheory(@PathVariable("themeId") int themeId,
                          @ModelAttribute Theory theory) {
        theoryService.addTheory(theory, themeId);
    }

    @GetMapping("/{themeId}")
    public Theory getTheoryByThemeId(@PathVariable("themeId") int themeId) {
        return theoryService.getTheoryByThemeId(themeId);
    }
}
