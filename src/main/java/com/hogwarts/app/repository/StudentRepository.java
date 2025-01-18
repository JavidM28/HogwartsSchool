package com.hogwarts.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hogwarts.app.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
