package com.hogwarts.app.controller;

import com.hogwarts.app.model.Faculty;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class FacultyControllerTest {

    @Autowired
    private RestTemplate restTemplate;

    private static final String BASE_URL = "http://localhost:8080/faculty";

    @Test
    void testGetFacultyInfo_Success() {
        ResponseEntity<Faculty> response = restTemplate.getForEntity(BASE_URL + "/1", Faculty.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Faculty faculty = response.getBody();
        assertThat(faculty).isNotNull();
        assertThat(faculty.getId()).isEqualTo(1L);
        assertThat(faculty.getName()).isEqualTo("Gryffindor");
        assertThat(faculty.getColor()).isEqualTo("Red");
    }

    @Test
    void testGetFacultyInfo_NotFound() {
        try {
            restTemplate.getForEntity(BASE_URL + "/99", Faculty.class);
        } catch (HttpClientErrorException ex) {
            assertThat(ex.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        }
    }

    @Test
    void testCreateFaculty_Success() {
        Faculty newFaculty = new Faculty(null, "Slytherin", "Green");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Faculty> request = new HttpEntity<>(newFaculty, headers);

        ResponseEntity<Faculty> response = restTemplate.postForEntity(BASE_URL, request, Faculty.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Faculty createdFaculty = response.getBody();
        assertThat(createdFaculty).isNotNull();
        assertThat(createdFaculty.getName()).isEqualTo("Slytherin");
        assertThat(createdFaculty.getColor()).isEqualTo("Green");
    }

    @Test
    void testCreateFaculty_InvalidInput() {
        Faculty invalidFaculty = new Faculty(null, "", "");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Faculty> request = new HttpEntity<>(invalidFaculty, headers);

        try {
            restTemplate.postForEntity(BASE_URL, request, String.class);
        } catch (HttpClientErrorException ex) {
            assertThat(ex.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        }
    }

    @Test
    void testEditFaculty_Success() {
        Faculty updatedFaculty = new Faculty(1L, "Gryffindor", "Scarlet");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Faculty> request = new HttpEntity<>(updatedFaculty, headers);

        ResponseEntity<Faculty> response = restTemplate.exchange(BASE_URL, HttpMethod.PUT, request, Faculty.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Faculty faculty = response.getBody();
        assertThat(faculty).isNotNull();
        assertThat(faculty.getColor()).isEqualTo("Scarlet");
    }

    @Test
    void testEditFaculty_InvalidInput() {
        Faculty invalidFaculty = new Faculty(null, "", "");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Faculty> request = new HttpEntity<>(invalidFaculty, headers);

        try {
            restTemplate.exchange(BASE_URL, HttpMethod.PUT, request, String.class);
        } catch (HttpClientErrorException ex) {
            assertThat(ex.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        }
    }

    @Test
    void testDeleteFaculty_Success() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Void> request = new HttpEntity<>(null, headers);

        ResponseEntity<Void> response = restTemplate.exchange(BASE_URL + "/1", HttpMethod.DELETE, request, Void.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void testDeleteFaculty_NotFound() {
        try {
            restTemplate.exchange(BASE_URL + "/99", HttpMethod.DELETE, null, Void.class);
        } catch (HttpClientErrorException ex) {
            assertThat(ex.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        }
    }
}
