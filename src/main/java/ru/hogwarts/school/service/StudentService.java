package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.HashMap;
import java.util.Map;

@Service
public class StudentService {
    private Map<Long, Student> students;
    private long idCounter;

    public StudentService() {
        students = new HashMap<>();
        idCounter = 0;
    }
    public Student getStudentById(long id) {
        return students.get(id);
    }
    public Student createStudent(String name, int age) {
        long id = idCounter++;
        Student student = new Student(id, name, age);
        students.put(id, student);
        return student;
    }


    public void updateStudent(Student student, String name, int age) {
        student.setName(name);
        student.setAge(age);
        students.put(student.getId(), student);
    }

    public void deleteStudent(Student student) {
        students.remove(student.getId());
    }
}
