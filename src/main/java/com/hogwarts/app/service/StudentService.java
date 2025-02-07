package com.hogwarts.app.service;

import com.hogwarts.app.model.Student;
import com.hogwarts.app.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import java.util.Collection;

@Service
public class StudentService {
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
        logger.info("Was invoked method addStudent");
        return studentRepository.save(student);
    }

    public Student findStudent(long id) {
        logger.info("Was invoked method findStudent with id = {}", id);
        return studentRepository.findById(id).orElseGet(() -> {
            logger.error("There is no student with id = {}", id);
            return null;
        });
    }

    public Student editStudent(Student student) {
        logger.info("Was invoked method editStudent");
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        logger.info("Was invoked method deleteStudent with id = {}", id);
        if (!studentRepository.existsById(id)) {
            logger.warn("Attempted to delete non-existing student with id = {}", id);
        }
        studentRepository.deleteById(id);
    }

    public Collection<Student> getAllStudent() {
        logger.info("Was invoked method getAllStudent");
        return studentRepository.findAll();
    }

    public Collection<Student> findByAgeBetween(int fromAge, int toAge) {
        logger.debug("Searching students with age between {} and {}", fromAge, toAge);
        return studentRepository.findByAgeBetween(fromAge, toAge);
    }

    public long countStudents() {
        logger.info("Was invoked method countStudents");
        return studentRepository.countAllStudents();
    }

    public double averageAge() {
        logger.info("Was invoked method averageAge");
        return studentRepository.averageAge();
    }

    public Collection<Student> findLastFiveStudents() {
        logger.info("Was invoked method findLastFiveStudents");
        return studentRepository.findLastFiveStudents();
    }
    public List<String> getStudentNamesStartingWithA() {
        logger.info("Was invoked method getStudentNamesStartingWithA");
        return studentRepository.findAll().stream()
                .map(student -> student.getName().toUpperCase())
                .filter(name -> name.startsWith("A"))
                .sorted()
                .collect(Collectors.toList());
    }

    public double getAverageStudentAge() {
        logger.info("Was invoked method getAverageStudentAge");
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .mapToInt(Student::getAge)
                .average()
                .orElse(0.0);
    }
    public String getLongestFacultyName() {
        logger.info("Was invoked method getLongestFacultyName");
        return studentRepository.findAll().stream()
                .map(student -> student.getFaculty() != null ? student.getFaculty().getName() : "")
                .max(Comparator.comparingInt(String::length))
                .orElse("");
    }

    public int calculateOptimizedSum() {
        logger.info("Was invoked method calculateOptimizedSum");
        return (1_000_000 * (1_000_000 + 1)) / 2; // O(1) формула суммы арифметической прогрессии
    }
}
