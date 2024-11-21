package ru.oksei.JournalAPI.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.oksei.JournalAPI.Mappers.UserMapper;
import ru.oksei.JournalAPI.Models.User;

@Component
public class UserDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public UserDAO(){
        this.jdbcTemplate = null;
    }

    public User getUserByLogin(String login) {
        return jdbcTemplate.query("SELECT * FROM User WHERE login = ?",
                new Object[]{login}, new UserMapper()).stream().findFirst().orElse(null);
    }

}
