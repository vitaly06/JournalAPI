package ru.oksei.JournalAPI.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.oksei.JournalAPI.Models.Class;
import ru.oksei.JournalAPI.Models.SchoolSubject;
import ru.oksei.JournalAPI.Repositories.ClassRepository;
import ru.oksei.JournalAPI.Repositories.SchoolSubjectRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SchoolSubjectService {
    private final ClassRepository classRepository;
    private SchoolSubjectRepository schoolSubjectRepository;
    @Autowired
    public SchoolSubjectService(SchoolSubjectRepository schoolSubjectRepository, ClassRepository classRepository) {
        this.schoolSubjectRepository = schoolSubjectRepository;
        this.classRepository = classRepository;
    }
    public Optional<SchoolSubject> getSubjectById(int id) {
        return schoolSubjectRepository.findById(id);
    }

    public List<SchoolSubject> getAllSubjects() {
        return schoolSubjectRepository.findAll();
    }

    @Transactional
    public void addSubject(SchoolSubject subject, int classId) {
        Class schoolClass = classRepository.findById(classId).get();
        schoolSubjectRepository.save(subject);
        schoolClass.getSubjects().add(subject);
        subject.getClasses().add(schoolClass);
        classRepository.save(schoolClass);
        schoolSubjectRepository.save(subject);
    }

    public List<SchoolSubject> getSubjectsByClassId(int classId){
        return schoolSubjectRepository.findSchoolSubjectsByClassId(classId);
    }
}
