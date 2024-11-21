package ru.oksei.JournalAPI.Mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.oksei.JournalAPI.Models.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setLogin(rs.getString("login"));
        user.setPassword(rs.getString("password"));
        return user;
    }
}
