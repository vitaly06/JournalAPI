package ru.oksei.JournalAPI.Models;

public class Class {
    private int classId;
    private String className;
    private int planForYear;

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

    public int getPlanForYear() {
        return planForYear;
    }

    public void setPlanForYear(int planForYear) {
        this.planForYear = planForYear;
    }
}
