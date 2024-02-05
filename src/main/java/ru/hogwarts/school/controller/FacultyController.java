package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    @Autowired
    private FacultyService facultyService;

    @GetMapping("/{id}")
    public ResponseEntity<Faculty> getFaculty(@PathVariable Long facultyId) {
        Faculty faculty = facultyService.getFacultyById(facultyId);
        return new ResponseEntity<>(faculty, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Faculty> createFaculty(@RequestParam String name, @RequestParam String color) {
        Faculty faculty = facultyService.createFaculty(name, color);
        return new ResponseEntity<>(faculty, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Faculty> updateFaculty(@PathVariable Long facultyId, @RequestParam String name, @RequestParam String color) {
        Faculty faculty = facultyService.updateFaculty(facultyId, name, color);
        return new ResponseEntity<>(faculty, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFaculty(@PathVariable Long facultyId) {
        facultyService.deleteFaculty(facultyId);
        return new ResponseEntity<>("Faculty deleted successfully", HttpStatus.OK);

    }

    @GetMapping("/color/{color}")
    public List<Faculty> getFacultyByColor(@PathVariable String color) {
        return facultyService.getFacultyByColor(color);
    }

    @GetMapping("/{id}/students")
    public List<Student> getStudentByFaculty(@PathVariable Long facultyId) {
        return facultyService.getStudentByFaculty(facultyId);
    }
}
