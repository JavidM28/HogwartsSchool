package com.hogwarts.app.controller;

import com.hogwarts.app.model.Student;
import com.hogwarts.app.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countStudents() {
        return ResponseEntity.ok(studentService.countStudents());
    }

    @GetMapping("/averageAge")
    public ResponseEntity<Double> averageAge() {
        return ResponseEntity.ok(studentService.averageAge());
    }

    @GetMapping("/lastFive")
    public ResponseEntity<Collection<Student>> findLastFiveStudents() {
        return ResponseEntity.ok(studentService.findLastFiveStudents());
    }
    @GetMapping("/names-starting-with-a")
    public ResponseEntity<List<String>> getStudentNamesStartingWithA() {
        return ResponseEntity.ok(studentService.getStudentNamesStartingWithA());
    }

    @GetMapping("/average-age")
    public ResponseEntity<Double> getAverageStudentAge() {
        return ResponseEntity.ok(studentService.getAverageStudentAge());
    }
    @GetMapping("/longest-faculty")
    public ResponseEntity<String> getLongestFacultyName() {
        return ResponseEntity.ok(studentService.getLongestFacultyName());
    }

    @GetMapping("/optimized-sum")
    public ResponseEntity<Integer> getOptimizedSum() {
        return ResponseEntity.ok(studentService.calculateOptimizedSum());
    }

}