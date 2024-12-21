package ru.oksei.JournalAPI.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.oksei.JournalAPI.Models.*;
import ru.oksei.JournalAPI.Models.Class;
import ru.oksei.JournalAPI.Repositories.*;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

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
//    @Transactional
//    public void addRecordToRunJournal(List<RunJournalRequest> runJournals, int offsetId, int classId){
//        // Удаляем старые записи
//
//
//        // Сохраняем новые записи
//        for (RunJournalRequest journal : runJournals) {
//            Optional<Class> class1 = classRepository.findById(classId);
//            Optional<Offset> offset = offsetRepository.findById(offsetId);
//            Optional<Student> student = studentRepository.findById(journal.getStudentId());
//            Optional<SchoolSubject> subject = schoolSubjectRepository.findById(journal.getSubjectId());
//
//            // создаём объект и заполняем
//            RunJournal runJournal = new RunJournal();
//            runJournal.setEstimation(journal.getEstimation());
//
//            // Проверяем наличие классов, смещений, студентов и предметов
//            class1.ifPresentOrElse(
//                    clazz -> {
//                        runJournal.setSchoolClass(clazz);
//                        System.out.println("Class set: " + clazz.getClassName());
//                    },
//                    () -> System.out.println("Class not found with id: " + classId)
//            );
//
//            offset.ifPresentOrElse(
//                    off -> {
//                        runJournal.setOffset(off);
//                        System.out.println("Offset set: " + off.getOffsetName());
//                    },
//                    () -> System.out.println("Offset not found with id: " + offsetId)
//            );
//
//            student.ifPresentOrElse(
//                    stud -> {
//                        runJournal.setStudent(stud);
//                        System.out.println("Student set: " + stud.getFullName());
//                    },
//                    () -> System.out.println("Student not found with id: " + journal.getStudentId())
//            );
//
//            subject.ifPresentOrElse(
//                    sub -> {
//                        runJournal.setSubject(sub);
//                        System.out.println("Subject set: " + sub.getSubjectName());
//                    },
//                    () -> System.out.println("Subject not found with id: " + journal.getSubjectId())
//            );
//
//            // Сохраняем RunJournal только если все необходимые данные присутствуют
//            if (class1.isPresent() && offset.isPresent() && student.isPresent() && subject.isPresent()) {
//                runJournalRepository.save(runJournal);
//                System.out.println("RunJournal saved successfully.");
//            } else {
//                System.out.println("RunJournal not saved due to missing data.");
//            }
//        }
//    }

    @Transactional
    public void setEstimationToStudents(List<StudentTime> students, int classId, int offsetId, int subjectId){
        runJournalRepository.deleteByOffsetIdAndClassId(offsetId, classId);
        Optional<Class> class1 = classRepository.findById(classId);
        Optional<Offset> offset = offsetRepository.findById(offsetId);
        Optional<SchoolSubject> subject = schoolSubjectRepository.findById(subjectId);

        // Дистанция забега
        String distance = offsetRepository.findById(offsetId).get().getDistance();
        long milliseconds;
        long minutes;
        long seconds;
        long millis;
        Duration duration;
        String estimation = "";
        Duration five;
        Duration four;
        Duration three;
        for (StudentTime student : students){
            RunJournal runJournal = new RunJournal();

            Optional<Student> student2 = studentRepository.findById(student.getStudentId());
            student2.ifPresentOrElse(
                    stud -> {
                        runJournal.setStudent(stud);
                        System.out.println("Student set: " + stud.getFullName());
                    },
                    () -> System.out.println("Student not found with id: " + student.getStudentId())
            );
            class1.ifPresentOrElse(
                    clazz -> {
                        runJournal.setSchoolClass(clazz);
                        System.out.println("Class set: " + clazz.getClassName());
                    },
                    () -> System.out.println("Class not found with id: " + classId)
            );

            offset.ifPresentOrElse(
                    off -> {
                        runJournal.setOffset(off);
                        System.out.println("Offset set: " + off.getOffsetName());
                    },
                    () -> System.out.println("Offset not found with id: " + offsetId)
            );

            subject.ifPresentOrElse(
                    sub -> {
                        runJournal.setSubject(sub);
                        System.out.println("Subject set: " + sub.getSubjectName());
                    },
                    () -> System.out.println("Subject not found with id: " + subjectId)
            );
            System.out.println(student.getTime());
            if (student.getTime() != null){
                milliseconds = Long.parseLong(student.getTime());
            } else{
                    runJournal.setEstimation(null);
                runJournalRepository.save(runJournal);
                continue;
            }

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
                estimation = "5";
            } else if (four.compareTo(resTime) >= 0) {
                System.out.println("4");
                estimation = "4";
            } else if (three.compareTo(resTime) >= 0) {
                System.out.println("3");
                estimation = "3";
            } else{
                estimation = "2";
            }
            runJournal.setEstimation(estimation);
            runJournal.setTime(formatDuration(resTime));
            runJournalRepository.save(runJournal);
        }
    }

    // Преобразование Duration в нужный формат
    public static String formatDuration(Duration duration) {
        long minutes = duration.toMinutes();
        long seconds = duration.getSeconds() % 60;
        long milliseconds = duration.toMillis() % 1000;
        // Форматирование строки
        return String.format("%d:%02d:%03d", minutes, seconds, milliseconds);
    }
}
