package com.hogwarts.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hogwarts.app.model.Student;

import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Collection<Student> findByAgeBetween(int fromAge, int toAge);
}
