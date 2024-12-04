package ru.oksei.JournalAPI.DAO;

import jakarta.validation.constraints.Negative;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.oksei.JournalAPI.Mappers.OffsetMapper;
import ru.oksei.JournalAPI.Mappers.RunJournalMapper;
import ru.oksei.JournalAPI.Mappers.ThemeJournalMapper;
import ru.oksei.JournalAPI.Models.RunJournal;
import ru.oksei.JournalAPI.Models.StudentTime;
import ru.oksei.JournalAPI.Models.ThemeJournal;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Duration;
import java.util.List;

@Component
public class RunJournalDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public RunJournalDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<RunJournal> getRunJournalByOffsetId(int offsetId) {
        return jdbcTemplate.query("SELECT * FROM RunJournal WHERE offsetId = ?",
                new Object[]{offsetId}, new RunJournalMapper());
    }

    public void setEstimationToStudents(List<StudentTime> students, int classId, int offsetId){
        // Дистанция забега
        String distance = jdbcTemplate.query("SELECT distance FROM Offset WHERE offsetId = ?",
                new Object[]{offsetId}, new OffsetMapper()).stream().findAny().orElse(null).getDistance();

        jdbcTemplate.batchUpdate("UPDATE RunJournal SET estimation = ? WHERE studentId = ? AND classId = ? AND themeId = ?",
                new BatchPreparedStatementSetter() {
                    long milliseconds;
                    long minutes;
                    long seconds;
                    long millis;
                    Duration duration;
                    String resString; // Строка оценки


                    // Устанавливаем нормативы
                    Duration five;
                    Duration four;
                    Duration three;


                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setInt(2, students.get(i).getStudentId());
                        ps.setInt(3, classId);
                        ps.setInt(4, offsetId);

                        milliseconds = Long.parseLong(students.get(i).getTime());
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
                        ps.setString(1, resString);
                    }

                    @Override
                    public int getBatchSize() {
                        return students.size();
                    }
                });
    }

    public void addRecordToRunJournal(List<RunJournal> runJournal, int classId, int offsetId) {
        jdbcTemplate.update("DELETE FROM RunJournal WHERE offsetId = ? AND classId = ?",
                offsetId, classId);
        jdbcTemplate.batchUpdate("INSERT INTO RunJournal(studentId, subjectId, offsetId, estimation, classId)"+
                        " VALUES(?, ?, ?, ?, ?)",
                new BatchPreparedStatementSetter() {

                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setInt(1, runJournal.get(i).getStudentId());
                        ps.setInt(2, runJournal.get(i).getSubjectId());
                        ps.setInt(3, runJournal.get(i).getOffsetId());
                        ps.setString(4, runJournal.get(i).getEstimation());
                        ps.setInt(5, runJournal.get(i).getClassId());

                    }

                    @Override
                    public int getBatchSize() {
                        return runJournal.size();
                    }
                });
    }
}
