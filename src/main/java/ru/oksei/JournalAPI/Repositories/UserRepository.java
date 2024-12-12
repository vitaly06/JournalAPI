package ru.oksei.JournalAPI.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.oksei.JournalAPI.Models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByLoginAndPassword(String login, String password);
}
