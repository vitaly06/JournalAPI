package ru.oksei.JournalAPI.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import javax.security.auth.Subject;
import java.util.List;

@Entity
@Table(name = "theme")
public class Theme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "themeid")
    private int themeId;
    @Column(name = "themename")
    private String themeName;
    @ManyToOne
    @JoinColumn(name = "subjectid")
    @JsonIgnore
    private SchoolSubject subject;
    @OneToMany(mappedBy = "theme")
    @JsonIgnore
    private List<ActivityJournal> activityJournals;

    public Theme() {}

    public Theme(String themeName) {
        this.themeName = themeName;
    }

    public int getThemeId() {
        return themeId;
    }

    public void setThemeId(int themeId) {
        this.themeId = themeId;
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public SchoolSubject getSubject() {
        return subject;
    }

    public void setSubject(SchoolSubject subject) {
        this.subject = subject;
    }

    public List<ActivityJournal> getActivityJournals() {
        return activityJournals;
    }

    public void setActivityJournals(List<ActivityJournal> activityJournals) {
        this.activityJournals = activityJournals;
    }
}
