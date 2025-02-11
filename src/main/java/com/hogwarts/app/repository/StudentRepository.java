package com.hogwarts.app.repository;

import com.hogwarts.app.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Collection<Student> findByAgeBetween(int fromAge, int toAge);

    @Query("SELECT COUNT(s) FROM Student s")
    long countAllStudents();

    @Query("SELECT AVG(s.age) FROM Student s")
    double averageAge();

    @Query("SELECT s FROM Student s ORDER BY s.id DESC LIMIT 5")
    Collection<Student> findLastFiveStudents();
}