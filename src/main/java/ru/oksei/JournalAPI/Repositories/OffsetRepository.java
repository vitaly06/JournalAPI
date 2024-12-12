package ru.oksei.JournalAPI.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.oksei.JournalAPI.Models.Offset;

import java.util.List;

@Repository
public interface OffsetRepository extends JpaRepository<Offset, Integer> {
    List<Offset> findAllBySubject_SubjectId(int subjectId);
}
