package ru.oksei.JournalAPI.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.oksei.JournalAPI.Models.Class;
import ru.oksei.JournalAPI.Repositories.ClassRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ClassService {
    private ClassRepository classRepository;
    @Autowired
    public ClassService(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    public Optional<Class> getClassById(int id) {
        return classRepository.findById(id);
    }

    public List<Class> getAllClasses() {
        return (List<Class>) classRepository.findAll();
    }

    @Transactional
    public void addClass(Class c) {
        classRepository.save(c);
    }
}
