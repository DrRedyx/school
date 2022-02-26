package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student addStud(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @GetMapping("{id}")
    public Student getStud(@PathVariable long id) {
        return studentService.findStudent(id);
    }

    @PutMapping
    public Student changeStud(@RequestBody Student student) {
        return studentService.updateStud(student);
    }

    @DeleteMapping("{id}")
    public Student deleteStud(@PathVariable long id) {
        return studentService.removeStud(id);
    }

    @GetMapping("{age}")
    public List<Student> getStudForAge(@PathVariable int age) {
        return studentService.getForAge(age);
    }
}
