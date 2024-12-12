package ru.oksei.JournalAPI.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.oksei.JournalAPI.Models.ThemeJournal;

import java.util.List;

@Repository
public interface ThemeJournalRepository extends JpaRepository<ThemeJournal, Integer> {
    List<ThemeJournal> findAllByTheme_ThemeId(int themeId);
    @Modifying
    @Transactional
    @Query("DELETE FROM ThemeJournal tj WHERE tj.theme.themeId = :themeId AND tj.clazz.classId = :classId")
    void deleteByThemeIdAndClassId(int themeId, int classId);
}
