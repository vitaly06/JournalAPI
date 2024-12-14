package ru.oksei.JournalAPI.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.oksei.JournalAPI.Models.*;
import ru.oksei.JournalAPI.Models.Class;
import ru.oksei.JournalAPI.Repositories.*;
import ru.oksei.JournalAPI.Requests.ThemeJournalRequest;

import java.util.List;
import java.util.Optional;

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
            Optional<Class> schoolClass = classRepository.findById(classId);
            Optional<Theme> theme = themeRepository.findById(themeId);
            Optional<Student> student = studentRepository.findById(journal.getStudentId());
            Optional<SchoolSubject> subject = schoolSubjectRepository.findById(journal.getSubjectId());

            // Создаем новый объект ThemeJournal
            ThemeJournal tj = new ThemeJournal();

            // Устанавливаем оценки и комментарии
            tj.setEstimation1(journal.getEstimation1());
            tj.setEstimation2(journal.getEstimation2());
            tj.setEstimation3(journal.getEstimation3());
            tj.setEstimation4(journal.getEstimation4());
            tj.setComent1(journal.getComent1());
            tj.setComent2(journal.getComent2());
            tj.setComent3(journal.getComent3());
            tj.setComent4(journal.getComent4());

            // Проверяем наличие классов, тем, студентов и предметов
            schoolClass.ifPresentOrElse(
                    clazz -> {
                        tj.setClazz(clazz);
                        System.out.println(clazz.getClassName());
                    },
                    () -> System.out.println("Class not found with id: " + classId)
            );

            theme.ifPresentOrElse(
                    t -> {
                        tj.setTheme(t);
                        System.out.println(t.getThemeName());
                    },
                    () -> System.out.println("Theme not found with id: " + themeId)
            );

            student.ifPresentOrElse(
                    s -> {
                        tj.setStudent(s);
                        System.out.println(s.getFullName());
                    },
                    () -> System.out.println("Student not found with id: " + journal.getStudentId())
            );

            subject.ifPresentOrElse(
                    sub -> {
                        tj.setSubject(sub);
                        System.out.println(sub.getSubjectName());
                    },
                    () -> System.out.println("Subject not found with id: " + journal.getSubjectId())
            );

            // Сохраняем ThemeJournal только если все необходимые данные присутствуют
            if (schoolClass.isPresent() && theme.isPresent() && student.isPresent() && subject.isPresent()) {
                themeJournalRepository.save(tj);
            } else {
                System.out.println("ThemeJournal not saved due to missing data.");
            }
        }
    }
}
