package ru.oksei.JournalAPI.Models;


public class RunJournal {
    private int recordId;
    private int studentId;
    private int subjectId;
    private int offsetId;
    private String estimation;
    private int classId;

    public RunJournal(int recordId, int studentId, int subjectId, int offsetId, int classId, String estimation) {
        this.recordId = recordId;
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.offsetId = offsetId;
        this.classId = classId;
        this.estimation = estimation;
    }

    public RunJournal() {}

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getOffsetId() {
        return offsetId;
    }

    public void setOffsetId(int offsetId) {
        this.offsetId = offsetId;
    }

    public String getEstimation() {
        return estimation;
    }

    public void setEstimation(String estimation) {
        this.estimation = estimation;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }
}
