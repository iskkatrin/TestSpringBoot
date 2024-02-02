package ru.hogwarts.school;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class FacultyControllerTestRest {
    @LocalServerPort
    private int port;

    @Autowired
    private FacultyController facultyController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetMessage() throws Exception {
        Assertions.assertThat(this.restTemplate.getForObject("http://localhost" + port + "/faculty", String.class))
                .isNotEmpty();
    }

    @Test
    public void testPostFaculty() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setName("Zoo");
        faculty.setColor("Pink");
        Assertions.assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/faculty", faculty, String.class))
                .isNotEmpty();
    }

    @Test
    public void testPutFaculty() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setName("Zoo");
        faculty.setColor("Pink");
        Assertions.assertThat(this.restTemplate.exchange
              ("http://localhost:" + port + "/faculty/{id}", HttpMethod.PUT, null, String.class, facultyId))
                .isNotEmpty();
        }

    @Test
    public void testDeleteFaculty() throws Exception {
        Long facultyId = 1L;
        ResponseEntity<String> response = restTemplate.exchange
                ("http://localhost:" + port + "/faculty/" + facultyId, HttpMethod.DELETE, null, String.class);
    }

    @Test
    public void TestGetFacultyByColor() throws Exception {
        String color = "Blue";
        List<Faculty> facultyList = restTemplate.getForObject("http://localhost:" + port + "/faculty/color/" + color, List.class);
        assertThat(facultyList).isNotNull();
        assertThat(facultyList).isNotEmpty();
    }

    @Test
    public void getStudentByFaculty() throws Exception {
        Long facultyId = 1L;
        List<Student> studentList = restTemplate.getForObject("http://localhost:" + port + "/faculty/" + facultyId, List.class);
        assertThat(studentList).isNotNull();
    }
}