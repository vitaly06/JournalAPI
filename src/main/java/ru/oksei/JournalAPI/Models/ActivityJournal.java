package ru.oksei.JournalAPI.Models;

public class ActivityJournal {
    private int recordId;
    private int studentId;
    private int subjectId;
    private int themeId;
    private int classId;
    private String theme1;
    private String theme2;
    private String theme3;
    private String theme4;
    private String activity1;
    private String activity2;
    private String activity3;
    private String activity4;

    public ActivityJournal(int recordId, int studentId, int subjectId, int themeId, int classId,
                           String theme1, String theme2, String theme3, String theme4,
                           String activity1, String activity2, String activity3, String activity4) {
        this.recordId = recordId;
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.themeId = themeId;
        this.classId = classId;
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
}
