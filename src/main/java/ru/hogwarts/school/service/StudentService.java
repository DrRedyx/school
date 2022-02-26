package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class StudentService {

    private final HashMap<Long, Student> studentMap;
    private long id = 0;

    public StudentService() {
        studentMap = new HashMap<>();
    }

    public Student addStudent(Student student) {
        student.setId(++id);
        studentMap.put(id, student);
        return student;
    }

    public Student findStudent(long id) {
        return studentMap.get(id);
    }

    public Student updateStud(Student student) {
        studentMap.put(student.getId(), student);
        return student;
    }

    public Student removeStud(long id) {
        return studentMap.remove(id);
    }

    public List<Student> getForAge(int age) {
        List<Student> students = new ArrayList<>();
        for (long id : studentMap.keySet()) {
            Student student = studentMap.get(id);
            if (student.getAge() == age) {
                students.add(student);
            }
        }
        return students;
    }

}
