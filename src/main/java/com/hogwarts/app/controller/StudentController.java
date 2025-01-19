package com.hogwarts.app.controller;

import org.springframework.web.bind.annotation.*;
import com.hogwarts.app.model.Student;
import com.hogwarts.app.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student.getName(), student.getAge());
    }

    @GetMapping("age-range")
    public List<Student> findByAgeRange(@RequestParam int min, @RequestParam int max) {
        return studentService.findByAgeBetween(min, max);
    }
}
