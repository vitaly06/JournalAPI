package ru.oksei.JournalAPI.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.oksei.JournalAPI.Models.*;
import ru.oksei.JournalAPI.Models.Class;
import ru.oksei.JournalAPI.Repositories.*;
import ru.oksei.JournalAPI.Requests.ActivityJournalRequest;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ActivityJournalService {
    private final ActivityJournalRepository activityJournalRepository;
    private final ClassRepository classRepository;
    private final ThemeRepository themeRepository;
    private final StudentRepository studentRepository;
    private final SchoolSubjectRepository schoolSubjectRepository;

    @Autowired
    public ActivityJournalService(ActivityJournalRepository activityJournalRepository, ClassRepository classRepository, ThemeRepository themeRepository, StudentRepository studentRepository, SchoolSubjectRepository schoolSubjectRepository) {
        this.activityJournalRepository = activityJournalRepository;
        this.classRepository = classRepository;
        this.themeRepository = themeRepository;
        this.studentRepository = studentRepository;
        this.schoolSubjectRepository = schoolSubjectRepository;
    }

    public List<ActivityJournal> getActivityJournalByThemeId(int themeId) {
        return activityJournalRepository.findAllByTheme_ThemeId(themeId);
    }

    @Transactional
    public void addRecordToActivityJournal(List<ActivityJournalRequest> activityJournals, int classId, int themeId){
        activityJournalRepository.deleteByOffsetIdAndClassId(themeId, classId);
        for (ActivityJournalRequest activityJournalRequest : activityJournals) {
            Optional<Class> schoolClass = classRepository.findById(classId);
            Optional<Theme> theme = themeRepository.findById(themeId);
            Optional<Student> student = studentRepository.findById(activityJournalRequest.getStudentId());
            Optional<SchoolSubject> subject = schoolSubjectRepository.findById(activityJournalRequest.getSubjectId());

            // создаём объект ActivityJournal и заполняем его
            ActivityJournal activityJournal = new ActivityJournal();
            activityJournal.setTheme1(activityJournalRequest.getTheme1());
            activityJournal.setTheme2(activityJournalRequest.getTheme2());
            activityJournal.setTheme3(activityJournalRequest.getTheme3());
            activityJournal.setTheme4(activityJournalRequest.getTheme4());
            activityJournal.setActivity1(activityJournalRequest.getActivity1());
            activityJournal.setActivity2(activityJournalRequest.getActivity2());
            activityJournal.setActivity3(activityJournalRequest.getActivity3());
            activityJournal.setActivity4(activityJournalRequest.getActivity4());

            // Проверяем наличие классов, тем, студентов и предметов
            schoolClass.ifPresentOrElse(
                    clazz -> {
                        activityJournal.setSchoolClass(clazz);
                        System.out.println("School class set: " + clazz.getClassName());
                    },
                    () -> System.out.println("School class not found with id: " + classId)
            );

            theme.ifPresentOrElse(
                    t -> {
                        activityJournal.setTheme(t);
                        System.out.println("Theme set: " + t.getThemeName());
                    },
                    () -> System.out.println("Theme not found with id: " + themeId)
            );

            student.ifPresentOrElse(
                    stud -> {
                        activityJournal.setStudent(stud);
                        System.out.println("Student set: " + stud.getFullName());
                    },
                    () -> System.out.println("Student not found with id: " + activityJournalRequest.getStudentId())
            );

            subject.ifPresentOrElse(
                    sub -> {
                        activityJournal.setSubject(sub);
                        System.out.println("Subject set: " + sub.getSubjectName());
                    },
                    () -> System.out.println("Subject not found with id: " + activityJournalRequest.getSubjectId())
            );

            // Сохраняем ActivityJournal только если все необходимые данные присутствуют
            if (schoolClass.isPresent() && theme.isPresent() && student.isPresent() && subject.isPresent()) {
                activityJournalRepository.save(activityJournal);
                System.out.println("ActivityJournal saved successfully.");
            } else {
                System.out.println("ActivityJournal not saved due to missing data.");
            }
        }
    }
}
