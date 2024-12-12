package ru.oksei.JournalAPI.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "'user'")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private int userId;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(){}

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
