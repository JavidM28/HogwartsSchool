package com.hogwarts.app.service;

import com.hogwarts.app.model.Faculty;
import com.hogwarts.app.repository.FacultyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private static final Logger logger = LoggerFactory.getLogger(FacultyService.class);
    private final FacultyRepository facultyRepository;

    @Autowired
    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty addFaculty(Faculty faculty) {
        logger.info("Was invoked method addFaculty");
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(long id) {
        logger.info("Was invoked method findFaculty with id = {}", id);
        return facultyRepository.findById(id).orElseGet(() -> {
            logger.error("There is no faculty with id = {}", id);
            return null;
        });
    }

    public Faculty editFaculty(Faculty faculty) {
        logger.info("Was invoked method editFaculty");
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(long id) {
        logger.info("Was invoked method deleteFaculty with id = {}", id);
        if (!facultyRepository.existsById(id)) {
            logger.warn("Attempted to delete non-existing faculty with id = {}", id);
        }
        facultyRepository.deleteById(id);
    }

    public List<Faculty> facultyColor(String color) {
        logger.debug("Searching faculties with color = {}", color);
        return facultyRepository.findAll().stream()
                .filter(faculty -> faculty.getColor().equals(color))
                .collect(Collectors.toList());
    }

    public Faculty findByNameIgnoreCaseAndColorIgnoreCase(String name, String color) {
        logger.info("Was invoked method findByNameIgnoreCaseAndColorIgnoreCase with name = {} and color = {}", name, color);
        return facultyRepository.findByNameIgnoreCase(name, color);
    }
}
