package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    public Faculty findByColorIgnoreCase(String color);
    public Faculty findByNameIgnoreCase(String name);
}
