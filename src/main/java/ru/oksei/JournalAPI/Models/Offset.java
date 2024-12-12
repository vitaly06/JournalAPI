package ru.oksei.JournalAPI.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "offset", schema = "public")
public class Offset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "offsetid")
    private int offsetId;
    @Column(name = "offsetname")
    private String offsetName;

    @Column(name = "distance")
    private String distance;
    @ManyToOne
    @JoinColumn(name = "subjectid")
    @JsonIgnore
    SchoolSubject subject;
    @OneToMany(mappedBy = "offset")
    @JsonIgnore
    private List<RunJournal> runJournals;
    public Offset(int offsetId, String offsetName, String distance) {
        this.offsetId = offsetId;
        this.offsetName = offsetName;
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

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public SchoolSubject getSubject() {
        return subject;
    }

    public void setSubject(SchoolSubject subject) {
        this.subject = subject;
    }

    public List<RunJournal> getRunJournals() {
        return runJournals;
    }

    public void setRunJournals(List<RunJournal> runJournals) {
        this.runJournals = runJournals;
    }
}
