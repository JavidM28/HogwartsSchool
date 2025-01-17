package com.hogwarts.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hogwarts.app.model.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
}
