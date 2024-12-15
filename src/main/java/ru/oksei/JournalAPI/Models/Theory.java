package ru.oksei.JournalAPI.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "theory")
public class Theory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "theoryid")
    private int theoryid;
    @Column(name = "theorytext")
    private String theoryText;
    @OneToOne
    @JoinColumn(name = "themeid")
    @JsonIgnore
    private Theme theme;

    public Theory() {}

    public Theory(String theoryText) {
        this.theoryText = theoryText;
    }

    public int getTheoryid() {
        return theoryid;
    }

    public void setTheoryid(int theoryid) {
        this.theoryid = theoryid;
    }

    public String getTheoryText() {
        return theoryText;
    }

    public void setTheoryText(String theoryText) {
        this.theoryText = theoryText;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }
}
