package com.hogwarts.app.controller;

import com.hogwarts.app.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testGetStudentInfo() {
        ResponseEntity<Student> response = restTemplate.getForEntity("/student/1", Student.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void testCreateStudent() {
        Student student = new Student(null, "Harry", 18);
        ResponseEntity<Student> response = restTemplate.postForEntity("/student", student, Student.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    void testEditStudent() {
        Student student = new Student(1L, "Harry Potter", 19);
        restTemplate.put("/student", student);
    }

    @Test
    void testDeleteStudent() {
        restTemplate.delete("/student/1");
    }

    @Test
    void testFindAll() {
        ResponseEntity<Student[]> response = restTemplate.getForEntity("/student", Student[].class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void testFindByAgeBetween() {
        ResponseEntity<Student[]> response = restTemplate.getForEntity("/student/byAgeBetween?fromAge=10&toAge=20", Student[].class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
