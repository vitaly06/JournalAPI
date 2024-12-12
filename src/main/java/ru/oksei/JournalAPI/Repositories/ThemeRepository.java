package ru.oksei.JournalAPI.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.oksei.JournalAPI.Models.Theme;

import java.util.List;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, Integer> {
    List<Theme> findAllBySubject_SubjectId(int subjectId);
}
