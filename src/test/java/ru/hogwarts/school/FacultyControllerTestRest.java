package ru.hogwarts.school;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
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
    public void testGetFaculty() throws Exception {
        Long facultyId = 1L;

        ResponseEntity<Faculty> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/faculty/" + facultyId, Faculty.class);
        Faculty faculty = responseEntity.getBody();

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(faculty).isNotNull();
        assertThat(faculty.getFacultyId()).isEqualTo(facultyId);
    }

    @Test
    public void testCreateFaculty() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setName("Zoo");
        faculty.setColor("Pink");
        String response = this.restTemplate.postForObject("http://localhost" + port + "/faculty", faculty, String.class);
        Assertions.assertThat(response).isNotEmpty();
        Assertions.assertThat(response).contains("Zoo", "Pink");
    }

    @Test
    public void testUpdateFaculty() throws Exception {
        Long facultyId = 1L;
        Faculty facultyToUpdate = new Faculty();
        facultyToUpdate.setName("Zoo");
        facultyToUpdate.setColor("Pink");
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Faculty> entity = new HttpEntity<>(facultyToUpdate, headers);

        ResponseEntity<Faculty> responseEntity = restTemplate.exchange
                ("http://localhost:" + port + "/faculty/" + facultyId, HttpMethod.PUT, entity, Faculty.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        Faculty updatedFaculty = responseEntity.getBody();
        assertThat(updatedFaculty).isNotNull();
        assertThat(updatedFaculty.getFacultyId()).isEqualTo(facultyId);
        assertThat(updatedFaculty.getName()).isEqualTo("Zoo");
        assertThat(updatedFaculty.getColor()).isEqualTo("Pink");
    }

    @Test
    public void testDeleteFaculty() throws Exception {
        Long facultyId = 1L;

        ResponseEntity<String> responseEntity = this.restTemplate.exchange("http://localhost:" + port + "/faculty/", HttpMethod.DELETE, null, String.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo("Faculty deleted successfully");
    }

    @Test
    public void testGetFacultyByColor() throws Exception {
        String color = "Blue";
        ResponseEntity <List<Faculty>> responseEntity = restTemplate.exchange(
                "http://localhost:" + port + "/faculty/color/" + color,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference <List<Faculty>>() {}
        );
        List<Faculty> facultyList = responseEntity.getBody();

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(facultyList).isNotNull();
    }

    @Test
    public void getStudentByFaculty() throws Exception {
        Long facultyId = 1L;
        ResponseEntity<List<Student>> responseEntity = restTemplate.exchange
                ("http://localhost:" + port + "/faculty/" + facultyId + "/students", HttpMethod.GET, null, new ParameterizedTypeReference<List<Student>>() {});
        List<Student> studentList = responseEntity.getBody();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(studentList).isNotNull();
    }
}