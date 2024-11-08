package ru.oksei.JournalAPI.Models;

public class RetakeJournal {
    private int recordId;
    private String retakeDate;
    private int studentId;
    private int subjectId;
    private int classId;

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public String getRetakeDate() {
        return retakeDate;
    }

    public void setRetakeDate(String retakeDate) {
        this.retakeDate = retakeDate;
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

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }
}
