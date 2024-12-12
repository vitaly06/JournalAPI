package ru.oksei.JournalAPI.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "runjournal")
public class RunJournal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recordid")
    private int recordId;
    @Column(name = "estimation")
    private String estimation;
    @ManyToOne
    @JoinColumn(name = "studentid")
    private Student student;
    @ManyToOne
    @JoinColumn(name = "subjectid")
    private SchoolSubject subject;
    @ManyToOne
    @JoinColumn(name = "offsetid")
    private Offset offset;
    @ManyToOne
    @JoinColumn(name = "classid")
    private Class schoolClass;

    public RunJournal(String estimation) {
        this.estimation = estimation;
    }

    public RunJournal() {}

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public String getEstimation() {
        return estimation;
    }

    public void setEstimation(String estimation) {
        this.estimation = estimation;
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

    public Offset getOffset() {
        return offset;
    }

    public void setOffset(Offset offset) {
        this.offset = offset;
    }

    public Class getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(Class schoolClass) {
        this.schoolClass = schoolClass;
    }
}
