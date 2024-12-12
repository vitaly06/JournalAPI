package ru.oksei.JournalAPI.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "subject")
public class SchoolSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subjectid")
    private int subjectId;
    @Column(name = "subjectname")
    private String subjectName;
    @ManyToMany(mappedBy = "subjects")
    @JsonIgnore
    private List<Class> classes = new ArrayList<>();
    @OneToMany(mappedBy = "subject")
    @JsonIgnore
    private List<Theme> themes;
    @OneToMany(mappedBy = "subject")
    @JsonIgnore
    private List<Offset> offsets;
    @OneToMany(mappedBy = "subject")
    @JsonIgnore
    private List<RunJournal> runJournals;
    @OneToMany(mappedBy = "subject")
    @JsonIgnore
    private List<ActivityJournal> activityJournals;


    public SchoolSubject() {
        this.classes = new ArrayList<>();
    }

    public SchoolSubject(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public List<Class> getClasses() {
        return classes;
    }

    public void setClasses(List<Class> classes) {
        this.classes = classes;
    }

    public List<Theme> getThemes() {
        return themes;
    }

    public void setThemes(List<Theme> themes) {
        this.themes = themes;
    }

    public List<Offset> getOffsets() {
        return offsets;
    }

    public void setOffsets(List<Offset> offsets) {
        this.offsets = offsets;
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
