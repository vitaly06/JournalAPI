package ru.oksei.JournalAPI.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "themejournal")
public class ThemeJournal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recordid")
    private int recordId;
    @Column(name = "estimation1")
    private String estimation1;
    @Column(name = "estimation2")
    private String estimation2;
    @Column(name = "estimation3")
    private String estimation3;
    @Column(name = "estimation4")
    private String estimation4;
    @Column(name = "coment1")
    private String coment1;
    @Column(name = "coment2")
    private String coment2;
    @Column(name = "coment3")
    private String coment3;
    @Column(name = "coment4")
    private String coment4;
    @ManyToOne
    @JoinColumn(name = "studentid")
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
    private Class clazz;

    public ThemeJournal() {}

    public ThemeJournal(String estimation1, String estimation2, String estimation3, String estimation4,
                        String coment1, String coment2, String coment3, String coment4) {
        this.estimation1 = estimation1;
        this.estimation2 = estimation2;
        this.estimation3 = estimation3;
        this.estimation4 = estimation4;
        this.coment1 = coment1;
        this.coment2 = coment2;
        this.coment3 = coment3;
        this.coment4 = coment4;
    }


    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }


    public String getEstimation1() {
        return estimation1;
    }

    public void setEstimation1(String estimation1) {
        this.estimation1 = estimation1;
    }

    public String getEstimation2() {
        return estimation2;
    }

    public void setEstimation2(String estimation2) {
        this.estimation2 = estimation2;
    }

    public String getEstimation3() {
        return estimation3;
    }

    public void setEstimation3(String estimation3) {
        this.estimation3 = estimation3;
    }

    public String getEstimation4() {
        return estimation4;
    }

    public void setEstimation4(String estimation4) {
        this.estimation4 = estimation4;
    }


    public String getComent1() {
        return coment1;
    }

    public void setComent1(String coment1) {
        this.coment1 = coment1;
    }

    public String getComent2() {
        return coment2;
    }

    public void setComent2(String coment2) {
        this.coment2 = coment2;
    }

    public String getComent3() {
        return coment3;
    }

    public void setComent3(String coment3) {
        this.coment3 = coment3;
    }

    public String getComent4() {
        return coment4;
    }

    public void setComent4(String coment4) {
        this.coment4 = coment4;
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

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }
}
