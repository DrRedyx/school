package ru.hogwarts.school.service;

import liquibase.pro.packaged.L;
import liquibase.pro.packaged.S;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentService {

    Logger logger = LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
        logger.info("Method created student");
        return studentRepository.save(student);
    }

    public Student findStudent(long id) {
        logger.info("Method find student");
        return studentRepository.findById(id).get();
    }

    public Student updateStud(Student student) {
        logger.info("Method rework student");
        return studentRepository.save(student);
    }

    public void removeStud(long id) {
        logger.info("Method delete student");
        studentRepository.deleteById(id);
    }

    public Collection<Student> getForAge(int age) {
        logger.info("Method get student by age");
        return studentRepository.findByAge(age);
    }

    public Collection<Student> getForAgeBetween(int min, int max) {
        logger.info("Method get students between age");
        return studentRepository.findByAgeBetween(min, max);
    }

    public Integer getNumberOfStudents() {
        logger.info("Method get number of students");
        return studentRepository.getNumberOfStudents();
    }

    public Integer getAverageAge() {
        logger.info("Method get average age of students");
        return studentRepository.getAverageAgeStudents();
    }

    public List<Student> getLast5Students() {
        logger.info("Method get last 5 students");
        return studentRepository.getLastStudents();
    }

    public List<String> getAllStudSortedByNameAndStartedByLetter() {
        return studentRepository.findAll().stream().
                parallel().
                map(Student::getName).
                map(String::toUpperCase).
                filter(s -> s.startsWith("А")).
                sorted().
                collect(Collectors.toList());
    }
    public Integer getAvgWithStream() {
        return (int) studentRepository.findAll().stream().
                mapToInt(Student::getAge).
                average().
                orElse(0);
    }
}
