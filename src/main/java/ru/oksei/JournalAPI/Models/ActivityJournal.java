package ru.oksei.JournalAPI.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "activityjournal")
public class ActivityJournal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recordid")
    private int recordId;
    @Column(name = "theme1")
    private String theme1;
    @Column(name = "theme2")
    private String theme2;
    @Column(name = "theme3")
    private String theme3;
    @Column(name = "theme4")
    private String theme4;
    @Column(name = "activity1")
    private String activity1;
    @Column(name = "activity2")
    private String activity2;
    @Column(name = "activity3")
    private String activity3;
    @Column(name = "activity4")
    private String activity4;
    @ManyToOne
    @JoinColumn(name = "studentid")
    @JsonIgnore
    private Student student;
    @ManyToOne
    @JoinColumn(name = "subjectid")
    @JsonIgnore
    private SchoolSubject subject;
    @ManyToOne
    @JoinColumn(name = "themeid")
    @JsonIgnore
    private Theme theme;
    @ManyToOne
    @JoinColumn(name = "classid")
    @JsonIgnore
    private Class schoolClass;


    public ActivityJournal(String theme1, String theme2, String theme3, String theme4,
                           String activity1, String activity2, String activity3, String activity4) {
        this.theme1 = theme1;
        this.theme2 = theme2;
        this.theme3 = theme3;
        this.theme4 = theme4;
        this.activity1 = activity1;
        this.activity2 = activity2;
        this.activity3 = activity3;
        this.activity4 = activity4;
    }

    public ActivityJournal() {}

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public String getTheme1() {
        return theme1;
    }

    public void setTheme1(String theme1) {
        this.theme1 = theme1;
    }

    public String getTheme2() {
        return theme2;
    }

    public void setTheme2(String theme2) {
        this.theme2 = theme2;
    }

    public String getTheme3() {
        return theme3;
    }

    public void setTheme3(String theme3) {
        this.theme3 = theme3;
    }

    public String getTheme4() {
        return theme4;
    }

    public void setTheme4(String theme4) {
        this.theme4 = theme4;
    }

    public String getActivity1() {
        return activity1;
    }

    public void setActivity1(String activity1) {
        this.activity1 = activity1;
    }

    public String getActivity2() {
        return activity2;
    }

    public void setActivity2(String activity2) {
        this.activity2 = activity2;
    }

    public String getActivity3() {
        return activity3;
    }

    public void setActivity3(String activity3) {
        this.activity3 = activity3;
    }

    public String getActivity4() {
        return activity4;
    }

    public void setActivity4(String activity4) {
        this.activity4 = activity4;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public SchoolSubject getSubject() {
        return subject;
    }

    public void setSubject(SchoolSubject subject) {
        this.subject = subject;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public Class getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(Class schoolClass) {
        this.schoolClass = schoolClass;
    }
}
