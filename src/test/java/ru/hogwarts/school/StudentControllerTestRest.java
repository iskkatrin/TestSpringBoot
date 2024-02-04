package ru.hogwarts.school;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.controller.StudentController;
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
    public void contextLoads() throws Exception {
        Assertions.assertThat(studentController).isNotNull();
    }

    @Test
    public void testDefaultMessage() throws Exception {
        Assertions.assertThat(this.restTemplate.getForObject("http://localhost" + port + "/", String.class))
                .isEqualTo("WebApp is working");
    }

    @Test
    public void testGetMessage() throws Exception {
        Assertions.assertThat(this.restTemplate.getForObject("http://localhost" + port + "/student", String.class))
                .isNotEmpty();
    }

    @Test
    public void testPostStudent() throws Exception {
        Student student = new Student();
        student.setName("Kate");
        Assertions.assertThat(this.restTemplate.postForObject("http://localhost" + port + "/student", student, String.class))
                .isNotEmpty();
    }


    @Test
    public void testPutStudent() throws Exception {
        Student student = new Student();
        student.setName("Mary");
        student.setAge(30);
        Assertions.assertThat(this.restTemplate.getForObject("http://localhost" + port + "/faculty/{id}", String.class))
                .isNotEmpty();
    }

    @Test
    public void testDeleteStudent() throws Exception {
        Long studentId = 1L;
        ResponseEntity<String> responseEntity = this.restTemplate.exchange("http://localhost:" + port + "/student/" + studentId, HttpMethod.DELETE, null, String.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo("Student deleted successfully");
    }

    @Test
    public void testGetStudentsByAge() throws Exception {
        Integer age = 30;
        ResponseEntity<List> responseEntity = this.restTemplate.getForEntity("http://localhost:" + port + "/student/age/" + age, List.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody()).isNotNull();
    }

    @Test
    public void getFacultyByStudentId() throws Exception {
        Long facultyId = 1L;
        List<Student> studentList = restTemplate.getForObject("http://localhost:" + port + "/faculty/" + facultyId, List.class);
        assertThat(studentList).isNotNull();
        assertThat(studentList).isNotNull();
    }
}

