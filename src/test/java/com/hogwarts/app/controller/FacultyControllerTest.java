package com.hogwarts.app.controller;

import com.hogwarts.app.model.Faculty;
import com.hogwarts.app.service.FacultyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(FacultyController.class)
public class FacultyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private FacultyService facultyService;

    @Test
    void testGetFacultyInfo() throws Exception {
        when(facultyService.findFaculty(1L)).thenReturn(new Faculty(1L, "Gryffindor", "Red"));
        mockMvc.perform(get("/faculty/1"))
                .andExpect(status().isOk());
    }
}
