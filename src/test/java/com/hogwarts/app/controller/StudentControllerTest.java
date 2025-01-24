package com.hogwarts.app.controller;

import com.hogwarts.app.model.Student;
import com.hogwarts.app.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@ExtendWith(SpringExtension.class)
@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private StudentService studentService;

    @Test
    void testGetStudentInfo() throws Exception {
        Student mockStudent = new Student(1L, "Harry", 18);
        when(studentService.findStudent(1L)).thenReturn(mockStudent);

        mockMvc.perform(get("/student/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Harry"))
                .andExpect(jsonPath("$.age").value(18));
    }

    @Test
    void testCreateStudent() throws Exception {
        Student newStudent = new Student(2L, "Hermione", 18);
        when(studentService.addStudent(newStudent)).thenReturn(newStudent);

        String requestBody = "{\"id\":2,\"name\":\"Hermione\",\"age\":18}";

        mockMvc.perform(post("/student")
                        .contentType(APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2L))
                .andExpect(jsonPath("$.name").value("Hermione"))
                .andExpect(jsonPath("$.age").value(18));
    }

    @Test
    void testEditStudent() throws Exception {
        Student updatedStudent = new Student(1L, "Harry", 19);
        when(studentService.editStudent(updatedStudent)).thenReturn(updatedStudent);

        String requestBody = "{\"id\":1,\"name\":\"Harry\",\"age\":19}";

        mockMvc.perform(put("/student")
                        .contentType(APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Harry"))
                .andExpect(jsonPath("$.age").value(19));
    }

    @Test
    void testDeleteStudent() throws Exception {
        mockMvc.perform(delete("/student/1"))
                .andExpect(status().isOk());
    }
}
