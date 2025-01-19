package com.hogwarts.app.service;

import org.springframework.stereotype.Service;
import com.hogwarts.app.model.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final Map<Long, Student> students = new HashMap<>();
    private long counter = 1;

    public Student createStudent(String name, int age) {
        Student student = new Student(counter++, name, age);
        students.put(student.getId(), student);
        return student;
    }

    public Student getStudent(Long id) {
        return students.get(id);
    }

    public List<Student> findByAgeBetween(int min, int max) {
        return students.values().stream()
                .filter(student -> student.getAge() >= min && student.getAge() <= max)
                .collect(Collectors.toList());
    }
}
