package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.List;

@Service
public class FacultyService {
    @Autowired
    private final FacultyRepository facultyRepository;
    @Autowired
    private final Faculty faculty;
    public FacultyService(FacultyRepository facultyRepository, Faculty faculty) {
        this.facultyRepository = facultyRepository;
        this.faculty = faculty;
    }

    public Faculty createFaculty(String name, String color) {
        return facultyRepository.save(faculty);
    }

    public Faculty getFacultyById(long id) {
        return facultyRepository.findById(id).get();
    }

    public void updateFaculty(String name, String color) {
        faculty.setName(name);
        faculty.setColor(color);
        facultyRepository.save(faculty);
    }

    public void deleteFaculty(long id) {
        facultyRepository.deleteById(id);
    }

    public List<Faculty> getFacultyByColor(String color) {
        return facultyRepository.findByColor(color);
    }
}

