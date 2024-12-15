package ru.oksei.JournalAPI.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.oksei.JournalAPI.Models.Theme;
import ru.oksei.JournalAPI.Models.Theory;
import ru.oksei.JournalAPI.Repositories.ThemeRepository;
import ru.oksei.JournalAPI.Repositories.TheoryRepository;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class TheoryService {
    private final TheoryRepository theoryRepository;
    private final ThemeRepository themeRepository;

    @Autowired
    public TheoryService(TheoryRepository theoryRepository, ThemeRepository themeRepository) {
        this.theoryRepository = theoryRepository;
        this.themeRepository = themeRepository;
    }

    @Transactional
    public void addTheory(Theory theory, int themeId) {
        Optional<Theme> theme = themeRepository.findById(themeId);
        if (theme.isPresent()){
            Theme newTheme = theme.get();
            theory.setTheme(newTheme);
            newTheme.setTheory(theory);
            themeRepository.save(newTheme);
        }

        theoryRepository.save(theory);
    }

    public Theory getTheoryByThemeId(int themeId) {
        return theoryRepository.findByTheme_ThemeId(themeId);
    }
}
