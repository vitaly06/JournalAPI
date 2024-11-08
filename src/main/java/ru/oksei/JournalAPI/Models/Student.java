package ru.oksei.JournalAPI.Models;

public class Student {
    private int studentId;
    private String fullName;
    private String dateOdfBirth;
    private int ClassId;

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDateOdfBirth() {
        return dateOdfBirth;
    }

    public void setDateOdfBirth(String dateOdfBirth) {
        this.dateOdfBirth = dateOdfBirth;
    }

    public int getClassId() {
        return ClassId;
    }

    public void setClassId(int classId) {
        ClassId = classId;
    }
}
