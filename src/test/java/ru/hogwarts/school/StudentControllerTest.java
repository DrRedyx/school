package ru.hogwarts.school;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void postStudentTest() throws Exception {
        Student student = new Student();
        student.setAge(22);
        student.setName("Geralt");
        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/student", student, String.class))
                .isNotNull();
    }

    @Test
    public void getStudentForIdTest() throws Exception {
        long id = 4;
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student" + id, String.class))
                .isNotNull();
    }

    @Test
    public void getStudentForAge() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/age" + 15, Collection.class))
                .isNotNull();
    }

    @Test
    public void deleteStudents() throws Exception {
        Student student = new Student();
        student.setAge(22);
        student.setName("Geralt");
        long id = this.restTemplate.getForObject("http://localhost:" + port + "/student/" + 4, Student.class).getId();
        this.restTemplate.delete("http://localhost:" + port + "/student/age" + id);
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/age" + 15, Student.class))
                .isNotIn(student);
    }

    @Test
    public void putStudentTest() throws Exception {
        Student student = new Student();
        student.setAge(20);
        student.setName("Triss");
        long id = restTemplate.postForObject("http://localhost:" + port + "/student", student, Student.class).getId();
        student.setId(id);
        restTemplate.put("http://localhost:" + port + "/student/", student);
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/" + id, Student.class))
                .isEqualTo(student);
    }
}
