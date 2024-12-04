package ru.oksei.JournalAPI.Models;

public class Offset {
    private int offsetId;
    private String offsetName;
    private int subjectId;
    private String distance;

    public Offset(int offsetId, String offsetName, int subjectId, String distance) {
        this.offsetId = offsetId;
        this.offsetName = offsetName;
        this.subjectId = subjectId;
        this.distance = distance;
    }

    public Offset() {}

    public int getOffsetId() {
        return offsetId;
    }

    public void setOffsetId(int offsetId) {
        this.offsetId = offsetId;
    }

    public String getOffsetName() {
        return offsetName;
    }

    public void setOffsetName(String offsetName) {
        this.offsetName = offsetName;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
