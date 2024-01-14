package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.List;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    @Autowired
    private FacultyService facultyService;

    @GetMapping ("/{id}")
    public ResponseEntity<Faculty> getFaculty(@PathVariable long id) {
        Faculty faculty = facultyService.getFacultyById(id);
        return new ResponseEntity<>(faculty, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Faculty> createFaculty(@RequestParam String name, @RequestParam String color) {
        Faculty createdFaculty = facultyService.createFaculty(name, color);
        return new ResponseEntity<>(createdFaculty, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Faculty> updateFaculty(@PathVariable long id, @RequestParam String name, @RequestParam String color) {
        Faculty faculty = facultyService.getFacultyById(id);
        facultyService.updateFaculty(faculty, name, color);
        return new ResponseEntity<>(faculty, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFaculty(@PathVariable long id) {
        Faculty faculty = facultyService.getFacultyById(id);
        facultyService.deleteFaculty(faculty);
        return new ResponseEntity<>("Faculty deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/color/{color}")
    public List<Faculty> getFacultyByColor(@PathVariable String color) {
        return facultyService.getFacultyByColor(color);
    }
}
