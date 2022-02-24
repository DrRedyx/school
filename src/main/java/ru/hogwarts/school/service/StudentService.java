package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.HashMap;

@Service
public class StudentService {

    private final HashMap<Long, Student> studentMap;
    private long id = 0;

    public StudentService() {
        studentMap = new HashMap<>();
    }

    public Student addStudent(String name, int age) {
        Student student = new Student(name, age);
        student.setId(++id);
        studentMap.put(id, student);
        return student;
    }

    public Student findStudent(long id) {
        return studentMap.get(id);
    }

    public Student updateStud(String name, int age) {
        Student student = new Student(name, age);
        studentMap.put(student.getId(), student);
        return student;
    }

    public Student removeStud(long id) {
        return studentMap.remove(id);
    }

}
