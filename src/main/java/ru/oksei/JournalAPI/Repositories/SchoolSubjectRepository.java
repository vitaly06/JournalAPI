package ru.oksei.JournalAPI.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.oksei.JournalAPI.Models.SchoolSubject;

import java.util.List;

@Repository
public interface SchoolSubjectRepository extends JpaRepository<SchoolSubject, Integer> {
    @Query("SELECT s FROM Class c JOIN c.subjects s WHERE c.classId = :classId")
    List<SchoolSubject> findSchoolSubjectsByClassId(@Param("classId") int classId);
}
