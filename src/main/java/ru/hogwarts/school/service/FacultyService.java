package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Service
public class FacultyService {

    Logger logger = LoggerFactory.getLogger(FacultyService.class);

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }


    public Faculty addFaculty(Faculty faculty) {
        logger.info("Create Faculty");
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(long id) {
        logger.info("Find faculty");
        return facultyRepository.findById(id).get();
    }

    public Faculty updateFaculty(Faculty faculty) {
        logger.info("Rework faculty");
        return facultyRepository.save(faculty);
    }

    public void removeFaculty(long id) {
        logger.info("Delete faculty");
        facultyRepository.deleteById(id);
    }

    public Faculty getForColorOrName(String color, String name) {
        logger.info("Get faculty by name or color");
        return facultyRepository.findByColorIgnoreCaseOrNameIgnoreCase(color, name);
    }

}
