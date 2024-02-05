package ru.hogwarts.school;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class StudentControllerTestRest {
    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetStudent() throws Exception {
        Long studentId = 1L;

        ResponseEntity<Student> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/student/" + studentId, Student.class);
        Student student = responseEntity.getBody();

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(student).isNotNull();
        assertThat(student.getStudentId()).isEqualTo(studentId);
    }

    @Test
    public void testCreateStudent() throws Exception {
        Student student = new Student();
        student.setStudentId(7L);
        student.setName("Kate");
        student.setAge(27);
        String response = this.restTemplate.postForObject("http://localhost" + port + "/student", student, String.class);
        Assertions.assertThat(response).isNotEmpty();
        Assertions.assertThat(response).contains("Kate", "27");
    }

    @Test
    public void testUpdateStudent() throws Exception {
        Student student = new Student();
        student.setStudentId(7L);
        student.setName("Mary");
        student.setAge(30);
        Assertions.assertThat(this.restTemplate.getForObject("http://localhost" + port + "/faculty/{id}", String.class))
                .isNotEmpty();
    }

    @Test
    public void testDeleteStudent() throws Exception {
        Long studentId = 1L;
        ResponseEntity<String> responseEntity = this.restTemplate.exchange("http://localhost:" + port + "/student/" , HttpMethod.DELETE, null, String.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo("Student deleted successfully");
    }

    @Test
    public void testGetStudentsByAge() throws Exception {
        Integer age = 30;
        ResponseEntity<List> responseEntity = this.restTemplate.getForEntity("http://localhost:" + port + "/student/age/" + age, List.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
    }

    @Test
    public void getFacultyByStudentId() throws Exception {
        Long studentId = 1L;
        List<Student> studentList = restTemplate.getForObject("http://localhost:" + port + "/faculty/" + studentId, List.class);
        assertThat(studentList).isNotNull();
    }
}

