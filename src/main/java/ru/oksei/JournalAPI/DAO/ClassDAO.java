package ru.oksei.JournalAPI.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.oksei.JournalAPI.Mappers.ClassMapper;
import ru.oksei.JournalAPI.Models.Class;

import java.util.List;

@Component
public class ClassDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ClassDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public ClassDAO(){
        jdbcTemplate = null;
    }

    public Class getClassById(int id){
        return jdbcTemplate.query("SELECT * FROM Classes WHERE classId = ?", new Object[]{id},
                new ClassMapper()).stream().findAny().orElse(null);
    }

    public List<Class> getAllClasses(){
        return jdbcTemplate.query("SELECT * FROM Classes", new ClassMapper());
    }

    public void addClass(Class c){
        jdbcTemplate.update("INSERT INTO Classes(className, planForYear) VALUES(?,?)",
                c.getClassName(), c.getPlanForYear());
    }
}
