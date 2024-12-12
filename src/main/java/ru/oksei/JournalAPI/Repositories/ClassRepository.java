package ru.oksei.JournalAPI.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.oksei.JournalAPI.Models.Class;

@Repository
public interface ClassRepository extends CrudRepository<Class, Integer> {

}
