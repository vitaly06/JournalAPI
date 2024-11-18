package ru.oksei.JournalAPI.Models;

public class ThemeJournal {
    private int recordId;
    private int studentId;
    private int subjectId;
    private int themeId;
    private int classId;
    private int estimation1;
    private int estimation2;
    private int estimation3;
    private int estimation4;
    private String date;

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

    public int getThemeId() {
        return themeId;
    }

    public void setThemeId(int themeId) {
        this.themeId = themeId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getEstimation() {
        return estimation1;
    }

    public void setEstimation(int estimation1) {
        this.estimation1 = estimation1;
    }

    public int getEstimation2() {
        return estimation2;
    }

    public void setEstimation2(int estimation2) {
        this.estimation2 = estimation2;
    }

    public int getEstimation3() {
        return estimation3;
    }

    public void setEstimation3(int estimation3) {
        this.estimation3 = estimation3;
    }

    public int getEstimation4() {
        return estimation4;
    }

    public void setEstimation4(int estimation4) {
        this.estimation4 = estimation4;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
