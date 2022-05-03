package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student addStud(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStud(@PathVariable long id) {
        Student student = studentService.findStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PutMapping
    public ResponseEntity<Student> changeStud(@RequestBody Student student) {
        Student foundStudent = studentService.updateStud(student);
        if (foundStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteStud(@PathVariable long id) {
        studentService.removeStud(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/age/{age}")
    public ResponseEntity<Collection<Student>> getStudForAge(@PathVariable int age) {
        return ResponseEntity.ok(studentService.getForAge(age));
    }

    @GetMapping("/number-of-students")
    public ResponseEntity<Integer> getStudForAge() {
        return ResponseEntity.ok(studentService.getNumberOfStudents());
    }

    @GetMapping("/average-age")
    public ResponseEntity<Integer> getAverageAge() {
        return ResponseEntity.ok(studentService.getAverageAge());
    }

    @GetMapping("/get-5-last-students")
    public ResponseEntity<List<Student>> get5LastStudents() {
        return ResponseEntity.ok(studentService.getLast5Students());
    }
    @GetMapping("/average-age-stream")
    public ResponseEntity<Integer> getAverageAgeStream() {
        return ResponseEntity.ok(studentService.getAvgWithStream());
    }

    @GetMapping("/average-age")
    public ResponseEntity<List<String>> getAllStudSortedByNameAndStartedByLetter() {
        return ResponseEntity.ok(studentService.getAllStudSortedByNameAndStartedByLetter());
    }

    @GetMapping("/thread")
    public ResponseEntity getStudByThread() {
        studentService.getAllStudentForRandomWithThread();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/synchronization_thread")
    public ResponseEntity getStudBySynchrThread() {
        studentService.getAllStudentForSynchrWithThread();
        return ResponseEntity.ok().build();
    }

}
