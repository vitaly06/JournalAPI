package ru.oksei.JournalAPI.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.oksei.JournalAPI.Models.SchoolSubject;
import ru.oksei.JournalAPI.Models.Theme;
import ru.oksei.JournalAPI.Repositories.SchoolSubjectRepository;
import ru.oksei.JournalAPI.Repositories.ThemeRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ThemeService {
    private final ThemeRepository themeRepository;
    private final SchoolSubjectRepository schoolSubjectRepository;

    @Autowired
    public ThemeService(ThemeRepository themeRepository, SchoolSubjectRepository schoolSubjectRepository) {
        this.themeRepository = themeRepository;
        this.schoolSubjectRepository = schoolSubjectRepository;
    }

    public Optional<Theme> getThemeById(int id){
        return themeRepository.findById(id);
    }

    public List<Theme> getAllThemesBySubjectId(int subjectId){
        return themeRepository.findAllBySubject_SubjectId(subjectId);
    }

    @Transactional
    public void addTheme(Theme theme, int subjectId){
        SchoolSubject ss = schoolSubjectRepository.findById(subjectId).get();
        themeRepository.save(theme);
        ss.getThemes().add(theme);
        theme.setSubject(ss);
        themeRepository.save(theme);
        themeRepository.save(theme);
    }


}
