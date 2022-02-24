package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.HashMap;

@Service
public class FacultyService {

    private final HashMap<Long, Faculty> facultyMap;
    private long id = 0;

    public FacultyService() {
        facultyMap = new HashMap<>();
    }

    public Faculty addFaculty(String name, String color) {
        Faculty faculty = new Faculty(name, color);
        faculty.setId(++id);
        facultyMap.put(id, faculty);
        return faculty;
    }

    public Faculty findFaculty(long id) {
        return facultyMap.get(id);
    }

    public Faculty updateFaculty(String name, String color) {
        Faculty faculty = new Faculty(name, color);
        facultyMap.put(faculty.getId(), faculty);
        return faculty;
    }

    public Faculty removeFaculty(long id) {
        return facultyMap.remove(id);
    }

}
