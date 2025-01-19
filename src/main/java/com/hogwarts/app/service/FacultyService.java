package com.hogwarts.app.service;

import org.springframework.stereotype.Service;
import com.hogwarts.app.model.Faculty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private final Map<Long, Faculty> faculties = new HashMap<>();
    private long counter = 1;

    public Faculty createFaculty(Faculty faculty) {
        faculty.setId(counter++);
        faculties.put(faculty.getId(), faculty);
        return faculty;
    }

    public List<Faculty> findByNameOrColor(String keyword) {
        return faculties.values().stream()
                .filter(faculty -> faculty.getName().equalsIgnoreCase(keyword) || faculty.getColor().equalsIgnoreCase(keyword))
                .collect(Collectors.toList());
    }
}
