package ru.oksei.JournalAPI.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.oksei.JournalAPI.Models.*;
import ru.oksei.JournalAPI.Models.Class;
import ru.oksei.JournalAPI.Repositories.*;
import ru.oksei.JournalAPI.Requests.RunJournalRequest;


import java.time.Duration;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class RunJournalService {
    private final RunJournalRepository runJournalRepository;
    private final ClassRepository classRepository;
    private final OffsetRepository offsetRepository;
    private final StudentRepository studentRepository;
    private final SchoolSubjectRepository schoolSubjectRepository;

    @Autowired
    public RunJournalService(RunJournalRepository runJournalRepository, ClassRepository classRepository, OffsetRepository offsetRepository, StudentRepository studentRepository, SchoolSubjectRepository schoolSubjectRepository) {
        this.runJournalRepository = runJournalRepository;
        this.classRepository = classRepository;
        this.offsetRepository = offsetRepository;
        this.studentRepository = studentRepository;
        this.schoolSubjectRepository = schoolSubjectRepository;
    }

    public List<RunJournal> getRunJournalByOffsetId(int offsetId){
        return runJournalRepository.findAllByOffset_OffsetId(offsetId);
    }

    // добавление записи в журнал
    @Transactional
    public void addRecordToRunJournal(List<RunJournalRequest> runJournals, int offsetId, int classId){
        // Удаляем старые записи
        runJournalRepository.deleteByOffsetIdAndClassId(offsetId, classId);

        // Сохраняем новые записи
        for (RunJournalRequest journal : runJournals) {
            Class class1 = classRepository.findById(classId).get();
            Offset offset = offsetRepository.findById(offsetId).get();
            Student student = studentRepository.findById(journal.getStudentId()).get();
            SchoolSubject subject = schoolSubjectRepository.findById(journal.getSubjectId()).get();
            // создаём объект и заполняем
            RunJournal runJournal = new RunJournal();
            runJournalRepository.save(runJournal);
            runJournal.setEstimation(journal.getEstimation());
            runJournal.setStudent(student);
            runJournal.setSubject(subject);
            runJournal.setSchoolClass(class1);
            runJournal.setOffset(offset);
            runJournalRepository.save(runJournal);
        }
    }

    @Transactional
    public void setEstimationToStudents(List<StudentTime> students, int classId, int offsetId){
        // Дистанция забега
        String distance = offsetRepository.findById(offsetId).get().getDistance();
        long milliseconds;
        long minutes;
        long seconds;
        long millis;
        Duration duration;
        String resString = "";
        Duration five;
        Duration four;
        Duration three;
        RunJournal runJournal;
        for (StudentTime student : students){
            runJournal = runJournalRepository.findByStudent_StudentIdAndSchoolClass_ClassIdAndOffset_OffsetId(student.getStudentId(), classId, offsetId);

            milliseconds = Long.parseLong(student.getTime());
            duration = Duration.ofMillis(milliseconds);
            minutes = duration.toMinutes();
            seconds = duration.getSeconds() % 60; // остаток секунд
            millis = duration.toMillis() % 1000; // остаток миллисекунд
            Duration resTime = Duration.ofMinutes(minutes).plusSeconds(seconds).plusMillis(millis);
            // Сравнение с использованием compareTo
            switch (distance){
                case "60м":
                    five = Duration.ofSeconds(9).plusMillis(8);
                    four = Duration.ofSeconds(10).plusMillis(3);
                    three = Duration.ofSeconds(10).plusMillis(8);
                    break;
                case "100м":
                    five = Duration.ofSeconds(15).plusMillis(1);
                    four = Duration.ofSeconds(15).plusMillis(6);
                    three = Duration.ofSeconds(16).plusMillis(2);
                    break;
                case "500м":
                    five = Duration.ofMinutes(1).plusSeconds(50);
                    four = Duration.ofMinutes(1).plusSeconds(55);
                    three = Duration.ofMinutes(2);
                    break;
                case "1000м":
                    five = Duration.ofMinutes(4).plusSeconds(16);
                    four = Duration.ofMinutes(4).plusSeconds(30);
                    three = Duration.ofMinutes(4).plusSeconds(45);
                    break;
                default:
                    five = Duration.ofSeconds(9).plusMillis(8);
                    four = Duration.ofSeconds(10).plusMillis(3);
                    three = Duration.ofSeconds(10).plusMillis(8);
                    break;
            }

            if (five.compareTo(resTime) >= 0) {
                System.out.println("5");
                resString = "5(" + resTime + ")";
            } else if (four.compareTo(resTime) >= 0) {
                System.out.println("4");
                resString = "4(" + resTime + ")";
            } else if (three.compareTo(resTime) >= 0) {
                System.out.println("3");
                resString = "3(" + resTime + ")";
            }
            runJournal.setEstimation(resString);
            runJournalRepository.save(runJournal);
        }
    }
}
