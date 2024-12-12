package ru.oksei.JournalAPI.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "class")
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "classid")
    private int classId;
    @Column(name = "classname")
    private String className;
    @JsonIgnore
    @OneToMany(mappedBy = "schoolClass")
    private List<Student> students;
    @ManyToMany
    @JoinTable(
            name = "classsubject",
            joinColumns = {@JoinColumn(name = "classid")},
            inverseJoinColumns = {@JoinColumn(name = "subjectid")}
    )
    @JsonIgnore
    private List<SchoolSubject> subjects = new ArrayList<>();

    @OneToMany(mappedBy = "schoolClass")
    @JsonIgnore
    private List<RunJournal> runJournals;

    @OneToMany(mappedBy = "schoolClass")
    @JsonIgnore
    private List<ActivityJournal> activityJournals;

    public Class(String className) {
        this.className = className;
    }
    public Class() {}

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<SchoolSubject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SchoolSubject> subjects) {
        this.subjects = subjects;
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
