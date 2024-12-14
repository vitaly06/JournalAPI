package ru.oksei.JournalAPI.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.oksei.JournalAPI.Models.RunJournal;

import java.util.List;

@Repository
public interface RunJournalRepository extends JpaRepository<RunJournal, Integer> {
    List<RunJournal> findAllByOffset_OffsetId(int offsetId);
    @Modifying
    @Transactional
    @Query("DELETE FROM RunJournal rj WHERE rj.offset.offsetId = :offsetId AND rj.schoolClass.classId = :classId")
    void deleteByOffsetIdAndClassId(int offsetId, int classId);

    RunJournal findByStudent_StudentIdAndSchoolClass_ClassIdAndOffset_OffsetId(int studentId, int classId, int offsetId);
}
