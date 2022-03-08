package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student findStudent(long id) {

        return studentRepository.findById(id).get();
    }

    public Student updateStud(Student student) {
        return studentRepository.save(student);
    }

    public void removeStud(long id) {

        studentRepository.deleteById(id);
    }

    public Collection<Student> getForAge(int age) {
        return studentRepository.findByAge(age);
    }

    public Collection<Student> getForAgeBetween(int min, int max) {
        return studentRepository.findByAgeBetween(min, max);
    }
}
