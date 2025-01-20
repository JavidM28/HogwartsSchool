package com.hogwarts.app.controller;

import com.hogwarts.app.model.Faculty;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FacultyControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testGetFacultyInfo() {
        ResponseEntity<Faculty> response = restTemplate.getForEntity("/faculty/1", Faculty.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void testCreateFaculty() {
        Faculty faculty = new Faculty(null, "Gryffindor", "Red");
        ResponseEntity<Faculty> response = restTemplate.postForEntity("/faculty", faculty, Faculty.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    void testEditFaculty() {
        Faculty faculty = new Faculty(1L, "Gryffindor", "Scarlet");
        restTemplate.put("/faculty", faculty);
    }

    @Test
    void testDeleteFaculty() {
        restTemplate.delete("/faculty/1");
    }

    @Test
    void testColorsOfFaculty() {
        ResponseEntity<Faculty[]> response = restTemplate.getForEntity("/faculty?color=Red", Faculty[].class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void testFindByNameAndColor() {
        ResponseEntity<Faculty> response = restTemplate.getForEntity("/faculty/Color?name=Gryffindor&color=Red", Faculty.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
