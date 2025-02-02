package com.hogwarts.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.hogwarts.app.model.Faculty;
import com.hogwarts.app.service.FacultyService;

import java.util.*;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Faculty> getFacultyInfo(@PathVariable Long id) {
        Faculty faculty = facultyService.findFaculty(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.addFaculty(faculty);
    }

    @PutMapping
    public ResponseEntity<Faculty> editFaculty(@RequestBody Faculty faculty) {
        Faculty foundFaculty = facultyService.editFaculty(faculty);
        if (foundFaculty == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundFaculty);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping
    public List<Faculty> colorsOfFaculty(@RequestParam String color) {
        return facultyService.facultyColor(color);
    }

    @GetMapping("/Color")
    public ResponseEntity<Faculty> findByNameIgnoreCaseAndColorIgnoreCase(@RequestParam String name, @RequestParam(required = false) String color) {
        if (name != null && !name.isBlank() && color != null && !color.isBlank()) {
            return ResponseEntity.ok(facultyService.findByNameIgnoreCaseAndColorIgnoreCase(name, color));
        }
        return ResponseEntity.ok().build();
    }


}