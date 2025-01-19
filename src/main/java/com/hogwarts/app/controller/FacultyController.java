package com.hogwarts.app.controller;

import org.springframework.web.bind.annotation.*;
import com.hogwarts.app.model.Faculty;
import com.hogwarts.app.service.FacultyService;

import java.util.List;

@RestController
@RequestMapping("faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }

    @GetMapping("search")
    public List<Faculty> searchByNameOrColor(@RequestParam String keyword) {
        return facultyService.findByNameOrColor(keyword);
    }
}
