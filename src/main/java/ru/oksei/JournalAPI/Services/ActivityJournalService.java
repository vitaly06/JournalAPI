package ru.oksei.JournalAPI.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.oksei.JournalAPI.Models.*;
import ru.oksei.JournalAPI.Models.Class;
import ru.oksei.JournalAPI.Repositories.*;
import ru.oksei.JournalAPI.Requests.ActivityJournalRequest;

import java.util.List;

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
            Class schoolClass = classRepository.findById(classId).get();
            Theme theme = themeRepository.findById(themeId).get();
            Student student = studentRepository.findById(activityJournalRequest.getStudentId()).get();
            SchoolSubject subject = schoolSubjectRepository.findById(activityJournalRequest.getSubjectId()).get();
            ActivityJournal activityJournal = new ActivityJournal();
            activityJournalRepository.save(activityJournal);
            activityJournal.setTheme1(activityJournalRequest.getTheme1());
            activityJournal.setTheme2(activityJournalRequest.getTheme2());
            activityJournal.setTheme3(activityJournalRequest.getTheme3());
            activityJournal.setTheme4(activityJournalRequest.getTheme4());
            activityJournal.setActivity1(activityJournalRequest.getActivity1());
            activityJournal.setActivity2(activityJournalRequest.getActivity2());
            activityJournal.setActivity3(activityJournalRequest.getActivity3());
            activityJournal.setActivity4(activityJournalRequest.getActivity4());
            activityJournal.setTheme(theme);
            activityJournal.setStudent(student);
            activityJournal.setSubject(subject);
            activityJournal.setSchoolClass(schoolClass);
            activityJournalRepository.save(activityJournal);
        }
    }
}
