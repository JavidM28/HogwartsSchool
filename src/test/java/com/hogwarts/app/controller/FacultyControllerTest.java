package com.hogwarts.app.controller;

import com.hogwarts.app.model.Faculty;
import com.hogwarts.app.service.FacultyService;
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
@WebMvcTest(FacultyController.class)
public class FacultyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private FacultyService facultyService;

    @Test
    void testGetFacultyInfo() throws Exception {
        Faculty mockFaculty = new Faculty(1L, "Gryffindor", "Red");
        when(facultyService.findFaculty(1L)).thenReturn(mockFaculty);

        mockMvc.perform(get("/faculty/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Gryffindor"))
                .andExpect(jsonPath("$.color").value("Red"));
    }

    @Test
    void testCreateFaculty() throws Exception {
        Faculty newFaculty = new Faculty(2L, "Slytherin", "Green");
        when(facultyService.addFaculty(newFaculty)).thenReturn(newFaculty);

        String requestBody = "{\"id\":2,\"name\":\"Slytherin\",\"color\":\"Green\"}";

        mockMvc.perform(post("/faculty")
                        .contentType(APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2L))
                .andExpect(jsonPath("$.name").value("Slytherin"))
                .andExpect(jsonPath("$.color").value("Green"));
    }

    @Test
    void testEditFaculty() throws Exception {
        Faculty updatedFaculty = new Faculty(1L, "Gryffindor", "Scarlet");
        when(facultyService.editFaculty(updatedFaculty)).thenReturn(updatedFaculty);

        String requestBody = "{\"id\":1,\"name\":\"Gryffindor\",\"color\":\"Scarlet\"}";

        mockMvc.perform(put("/faculty")
                        .contentType(APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Gryffindor"))
                .andExpect(jsonPath("$.color").value("Scarlet"));
    }

    @Test
    void testDeleteFaculty() throws Exception {
        mockMvc.perform(delete("/faculty/1"))
                .andExpect(status().isOk());
    }

}
