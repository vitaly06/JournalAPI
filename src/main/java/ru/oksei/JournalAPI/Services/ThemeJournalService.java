package ru.oksei.JournalAPI.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.oksei.JournalAPI.Models.*;
import ru.oksei.JournalAPI.Models.Class;
import ru.oksei.JournalAPI.Repositories.*;
import ru.oksei.JournalAPI.Requests.ThemeJournalRequest;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ThemeJournalService {
    private final ThemeJournalRepository themeJournalRepository;
    private final ClassRepository classRepository;
    private final ThemeRepository themeRepository;
    private final StudentRepository studentRepository;
    private final SchoolSubjectRepository schoolSubjectRepository;

    @Autowired
    public ThemeJournalService(ThemeJournalRepository themeJournalRepository, ClassRepository classRepository,
                               ThemeRepository themeRepository, StudentRepository studentRepository, SchoolSubjectRepository schoolSubjectRepository) {
        this.themeJournalRepository = themeJournalRepository;
        this.classRepository = classRepository;
        this.themeRepository = themeRepository;
        this.studentRepository = studentRepository;
        this.schoolSubjectRepository = schoolSubjectRepository;
    }
    // журнал по themeId
    public List<ThemeJournal> getThemeJournalByThemeId(int themeId){
        return themeJournalRepository.findAllByTheme_ThemeId(themeId);
    }
    // добавление записи в журнал
    @Transactional
    public void addThemeJournal(List<ThemeJournalRequest> themeJournal, int themeId, int classId){
        // Удаляем старые записи
        themeJournalRepository.deleteByThemeIdAndClassId(themeId, classId);

        // Сохраняем новые записи
        for (ThemeJournalRequest journal : themeJournal) {
            Class schoolClass = classRepository.findById(classId).get();
            Theme theme = themeRepository.findById(themeId).get();
            Student student = studentRepository.findById(journal.getStudentId()).get();
            SchoolSubject subject = schoolSubjectRepository.findById(journal.getSubjectId()).get();
            ThemeJournal tj = new ThemeJournal();
            themeJournalRepository.save(tj);
            tj.setEstimation1(journal.getEstimation1());
            tj.setEstimation2(journal.getEstimation2());
            tj.setEstimation3(journal.getEstimation3());
            tj.setEstimation4(journal.getEstimation4());
            tj.setComent1(journal.getComent1());
            tj.setComent2(journal.getComent2());
            tj.setComent3(journal.getComent3());
            tj.setComent4(journal.getComent4());
            tj.setTheme(theme);
            tj.setStudent(student);
            tj.setSubject(subject);
            tj.setClazz(schoolClass);
            themeJournalRepository.save(tj);
        }
    }
}
