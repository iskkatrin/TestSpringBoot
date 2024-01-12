package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

@RestController
@RequestMapping("/student")
public class FacultyController {
    @Autowired
    private FacultyService facultyService;

    @GetMapping ("/{id}")
    public ResponseEntity<Faculty> getFaculty(@PathVariable int id) {
        Faculty faculty = facultyService.getFacultyById(id);
        return new ResponseEntity<>(faculty, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Faculty> createFaculty(@RequestBody Faculty faculty) {
        Faculty createdFaculty = facultyService.createFaculty(faculty);
        return new ResponseEntity<>(createdFaculty, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Faculty> updateFaculty(@PathVariable int id, @RequestBody Faculty faculty) {
        Faculty updatedFaculty = facultyService.updateFaculty(id, faculty);
        return new ResponseEntity<>(updatedFaculty, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFaculty(@PathVariable int id) {
        facultyService.deleteFaculty(id);
        return new ResponseEntity<>("Faculty deleted successfully", HttpStatus.OK);
    }
}
