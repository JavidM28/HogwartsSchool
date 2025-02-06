package com.hogwarts.app.controller;

import com.hogwarts.app.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class StudentControllerTest {

    @Autowired
    private RestTemplate restTemplate;

    private static final String BASE_URL = "http://localhost:8080/student";

    @Test
    void testGetStudentInfo_Success() {
        ResponseEntity<Student> response = restTemplate.getForEntity(BASE_URL + "/1", Student.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Student student = response.getBody();
        assertThat(student).isNotNull();
        assertThat(student.getId()).isEqualTo(1L);
        assertThat(student.getName()).isEqualTo("Harry");
        assertThat(student.getAge()).isEqualTo(18);
    }

    @Test
    void testGetStudentInfo_NotFound() {
        try {
            restTemplate.getForEntity(BASE_URL + "/99", Student.class);
        } catch (HttpClientErrorException ex) {
            assertThat(ex.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        }
    }

    @Test
    void testCreateStudent_Success() {
        Student newStudent = new Student(null, "Hermione", 18);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Student> request = new HttpEntity<>(newStudent, headers);

        ResponseEntity<Student> response = restTemplate.postForEntity(BASE_URL, request, Student.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Student createdStudent = response.getBody();
        assertThat(createdStudent).isNotNull();
        assertThat(createdStudent.getName()).isEqualTo("Hermione");
        assertThat(createdStudent.getAge()).isEqualTo(18);
    }

    @Test
    void testCreateStudent_InvalidInput() {
        Student invalidStudent = new Student(null, "", -1);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Student> request = new HttpEntity<>(invalidStudent, headers);

        try {
            restTemplate.postForEntity(BASE_URL, request, String.class);
        } catch (HttpClientErrorException ex) {
            assertThat(ex.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        }
    }

    @Test
    void testDeleteStudent_Success() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Void> request = new HttpEntity<>(null, headers);

        ResponseEntity<Void> response = restTemplate.exchange(BASE_URL + "/1", HttpMethod.DELETE, request, Void.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void testDeleteStudent_NotFound() {
        try {
            restTemplate.exchange(BASE_URL + "/99", HttpMethod.DELETE, null, Void.class);
        } catch (HttpClientErrorException ex) {
            assertThat(ex.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        }
    }
}
