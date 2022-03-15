package ru.hogwarts.school;

import org.checkerframework.checker.units.qual.C;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.service.FacultyService;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.hogwarts.school.TestConstants.*;

@WebMvcTest(controllers = FacultyController.class)
public class FacultyControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacultyRepository facultyRepository;

    @SpyBean
    private FacultyService facultyService;

    @InjectMocks
    private FacultyController facultyController;


    @Test
    public void createFacultyTest() throws Exception {
        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", NAME);
        facultyObject.put("color", COLOR);
        Faculty faculty = new Faculty();
        faculty.setId(ID);
        faculty.setColor(COLOR);
        faculty.setName(NAME);
        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculty")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID))
                .andExpect(jsonPath("$.name").value(NAME))
                .andExpect(jsonPath("$.color").value(COLOR));

    }

    @Test
    public void findFacultyForIdTest() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setId(ID);
        faculty.setColor(COLOR);
        faculty.setName(NAME);
        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/" + ID)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID))
                .andExpect(jsonPath("$.name").value(NAME))
                .andExpect(jsonPath("$.color").value(COLOR));
    }

    @Test
    public void updateFaculty() throws Exception {
        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", NAME);
        facultyObject.put("color", COLOR);
        Faculty faculty = new Faculty();
        faculty.setId(ID);
        faculty.setColor(COLOR);
        faculty.setName(NAME);
        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculty")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID))
                .andExpect(jsonPath("$.name").value(NAME))
                .andExpect(jsonPath("$.color").value(COLOR));
    }

    @Test
    public void deleteFacultyTest() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setId(ID);
        faculty.setColor(COLOR);
        faculty.setName(NAME);
        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
        doNothing().when(facultyRepository).deleteById(any(Long.class));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/delete/" + ID))
                .andExpect(status().isOk());
    }

    @Test
    public void findByColorOrName() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setId(ID);
        faculty.setColor(COLOR);
        faculty.setName(NAME);
        when(facultyRepository.findByColorIgnoreCaseOrNameIgnoreCase(any(String.class), any(String.class))).thenReturn(faculty);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/?color=" + COLOR)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID))
                .andExpect(jsonPath("$.name").value(NAME))
                .andExpect(jsonPath("$.color").value(COLOR));
    }



}
