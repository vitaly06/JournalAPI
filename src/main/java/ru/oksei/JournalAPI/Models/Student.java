package ru.oksei.JournalAPI.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "studentid")
    private int studentId;
    @Column(name = "fullname")
    private String fullName;
    @Column(name = "dateofbirth")
    private String dateOfBirth;
    @ManyToOne
    @JoinColumn(name = "classid")
    @JsonIgnore
    private Class schoolClass;
    @OneToMany(mappedBy = "student")
    @JsonIgnore
    private List<RunJournal> runJournals;
    @OneToMany(mappedBy = "student")
    @JsonIgnore
    private List<ActivityJournal> activityJournals;

    public Student() {}

    public Student(String fullName, String dateOfBirth) {
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Class getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(Class schoolClass) {
        this.schoolClass = schoolClass;
    }

    public List<RunJournal> getRunJournals() {
        return runJournals;
    }

    public void setRunJournals(List<RunJournal> runJournals) {
        this.runJournals = runJournals;
    }

    public List<ActivityJournal> getActivityJournals() {
        return activityJournals;
    }

    public void setActivityJournals(List<ActivityJournal> activityJournals) {
        this.activityJournals = activityJournals;
    }
}
