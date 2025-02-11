package com.hogwarts.app.controller;

import com.hogwarts.app.model.Student;
import com.hogwarts.app.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

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

    @GetMapping("/students/print-parallel")
    public ResponseEntity<String> printStudentNamesInParallel() {
        Collection<Student> students = studentService.getAllStudent();

        if (students.size() < 6) {
            return ResponseEntity.badRequest().body("Not enough students (minimum 6 required).");
        }

        List<Student> studentList = List.copyOf(students);

        printStudentName(studentList.get(0).getName());
        printStudentName(studentList.get(1).getName());

        CompletableFuture<Void> thread1 = CompletableFuture.runAsync(() -> {
            printStudentName(studentList.get(2).getName());
            printStudentName(studentList.get(3).getName());
        });

        CompletableFuture<Void> thread2 = CompletableFuture.runAsync(() -> {
            printStudentName(studentList.get(4).getName());
            printStudentName(studentList.get(5).getName());
        });

        try {
            CompletableFuture.allOf(thread1, thread2).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error occurred while printing names.");
        }

        return ResponseEntity.ok("Names printed in parallel.");
    }

    @GetMapping("/students/print-synchronized")
    public ResponseEntity<String> printStudentNamesSynchronized() {
        Collection<Student> students = studentService.getAllStudent();

        if (students.size() < 6) {
            return ResponseEntity.badRequest().body("Not enough students (minimum 6 required).");
        }

        List<Student> studentList = List.copyOf(students);

        printStudentNameSynchronized(studentList.get(0).getName());
        printStudentNameSynchronized(studentList.get(1).getName());

        CompletableFuture<Void> thread1 = CompletableFuture.runAsync(() -> {
            printStudentNameSynchronized(studentList.get(2).getName());
            printStudentNameSynchronized(studentList.get(3).getName());
        });

        CompletableFuture<Void> thread2 = CompletableFuture.runAsync(() -> {
            printStudentNameSynchronized(studentList.get(4).getName());
            printStudentNameSynchronized(studentList.get(5).getName());
        });

        try {
            CompletableFuture.allOf(thread1, thread2).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error occurred while printing names.");
        }

        return ResponseEntity.ok("Names printed in synchronized mode.");
    }

    private synchronized void printStudentNameSynchronized(String name) {
        System.out.println("Thread: " + Thread.currentThread().getName() + " - Name: " + name);
    }

    private void printStudentName(String name) {
        System.out.println("Thread: " + Thread.currentThread().getName() + " - Name: " + name);
    }
}