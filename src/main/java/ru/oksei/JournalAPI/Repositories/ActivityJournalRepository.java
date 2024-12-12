package ru.oksei.JournalAPI.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.oksei.JournalAPI.Models.ActivityJournal;

import java.util.List;
@Repository
public interface ActivityJournalRepository extends JpaRepository<ActivityJournal, Integer> {
    List<ActivityJournal> findAllByTheme_ThemeId(int themeId);
    @Modifying
    @Transactional
    @Query("DELETE FROM ActivityJournal aj WHERE aj.theme.themeId = :themeId AND aj.schoolClass.classId = :classId")
    void deleteByOffsetIdAndClassId(int themeId, int classId);
}
