package se.lexicon.course_manager_assignment.data.service.converter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.course_manager_assignment.model.Course;
import se.lexicon.course_manager_assignment.model.Student;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = {ModelToDto.class})
public class ModelToDtoTest {

    @Autowired
    private Converters testObject;
    LocalDate javaStartDate = LocalDate.of(2021, 01, 05);
    LocalDate PythonStartDate = LocalDate.of(2019, 04, 03);


    Course course = new Course(1, "Java", javaStartDate, 3);
    Course course1 = new Course("Python", PythonStartDate, 7);

    Student student = new Student(2, "Aman", "23pinkpanda@gmail.com", "Sweden");
    Student student1 = new Student(3, "Sara", "test", "test");


    @Test
    @DisplayName("Test context successfully setup")
    void context_loads() {
        assertNotNull(testObject);
    }

    //write your tests here
}
