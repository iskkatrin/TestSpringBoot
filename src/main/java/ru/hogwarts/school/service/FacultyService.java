package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import java.util.HashMap;
import java.util.Map;

@Service
public class FacultyService {
    private Map<Long, Faculty> faculties;
    private long idCounter;

    public FacultyService() {
        faculties = new HashMap<>();
        idCounter = 0;
    }
    public Faculty createFaculty (String name, String color) {
        long id = idCounter++;
        Faculty faculty = new Faculty(id, name, color);
        faculties.put(id, faculty);
        return faculty;
    }
    public Faculty getFacultyById(long id ) {
        return faculties.get(id);
    }
    public void updateFaculty (Faculty faculty, String name, String color) {
        faculty.setName(name);
        faculty.setColor(color);
        faculties.put(faculty.getId(), faculty);
    }
    public void deleteFaculty (Faculty faculty) {
        faculties.remove(faculty.getId());
    }
}

