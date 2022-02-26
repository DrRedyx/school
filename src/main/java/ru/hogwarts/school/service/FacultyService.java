package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class FacultyService {

    private final HashMap<Long, Faculty> facultyMap;
    private long id = 0;

    public FacultyService() {
        facultyMap = new HashMap<>();
    }

    public Faculty addFaculty(Faculty faculty) {
        faculty.setId(++id);
        facultyMap.put(id, faculty);
        return faculty;
    }

    public Faculty findFaculty(long id) {
        return facultyMap.get(id);
    }

    public Faculty updateFaculty(Faculty faculty) {
        facultyMap.put(faculty.getId(), faculty);
        return faculty;
    }

    public Faculty removeFaculty(long id) {
        return facultyMap.remove(id);
    }

    public List<Faculty> getForColor(String color) {
        List<Faculty> faculties = new ArrayList<>();
        for (long id : facultyMap.keySet()) {
            Faculty faculty = facultyMap.get(id);
            if (faculty.getColor().equals(color)) {
                faculties.add(faculty);
            }
        }
        return faculties;
    }

}
