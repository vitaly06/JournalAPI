package ru.oksei.JournalAPI.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.oksei.JournalAPI.Models.Theory;

@Repository
public interface TheoryRepository extends JpaRepository<Theory, Integer> {
    Theory findByTheme_ThemeId(int themeId);
}
