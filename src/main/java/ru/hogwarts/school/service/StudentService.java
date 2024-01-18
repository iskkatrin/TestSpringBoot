package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.List;



@Service
public class StudentService {
    @Autowired
    private final StudentRepository studentRepository;
    @Autowired
    private final Student student;

    public StudentService(StudentRepository studentRepository, Student student) {
        this.studentRepository = studentRepository;
        this.student = student;
    }

    public Student getStudentById(long id) {
        return studentRepository.findById(id).get();
    }

    public Student createStudent(String name, int age) {
        return studentRepository.save(student);
    }


    public void updateStudent(String name, int age) {
        student.setName(name);
        student.setAge(age);
        studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }

    public List<Student> getStudentsByAge(int age) {
        return studentRepository.getByAge(age);
    }
}