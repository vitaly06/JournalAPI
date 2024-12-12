package ru.oksei.JournalAPI.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.oksei.JournalAPI.Models.Offset;
import ru.oksei.JournalAPI.Models.SchoolSubject;
import ru.oksei.JournalAPI.Repositories.OffsetRepository;
import ru.oksei.JournalAPI.Repositories.SchoolSubjectRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class OffsetService {
    private final OffsetRepository offsetRepository;
    private final SchoolSubjectRepository schoolSubjectRepository;

    @Autowired
    public OffsetService(OffsetRepository offsetRepository, SchoolSubjectRepository schoolSubjectRepository) {
        this.offsetRepository = offsetRepository;
        this.schoolSubjectRepository = schoolSubjectRepository;
    }

    public Offset getOffsetById(int id) {
        return offsetRepository.findById(id).orElse(null);
    }

    public List<Offset> getAllOffsetsBySubjectId(int subjectId) {
        return offsetRepository.findAllBySubject_SubjectId(subjectId);
    }

    @Transactional
    public void addOffset(Offset offset, int subjectId) {
        offsetRepository.save(offset);
        SchoolSubject subject = schoolSubjectRepository.findById(subjectId).orElse(null);
        subject.getOffsets().add(offset);
        offset.setSubject(subject);
        schoolSubjectRepository.save(subject);
        offsetRepository.save(offset);

    }


}
