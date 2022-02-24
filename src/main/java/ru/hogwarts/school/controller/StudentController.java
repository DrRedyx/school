package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("{name}{age}")
    public Student addStud(@PathVariable String name, @PathVariable int age) {
        return studentService.addStudent(name, age);
    }

    @GetMapping("{id}")
    public Student getStud(@PathVariable long id) {
        return studentService.findStudent(id);
    }
}
