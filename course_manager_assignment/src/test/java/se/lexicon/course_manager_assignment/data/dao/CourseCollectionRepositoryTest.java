package se.lexicon.course_manager_assignment.data.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.course_manager_assignment.data.sequencers.CourseSequencer;
import se.lexicon.course_manager_assignment.model.Course;
import se.lexicon.course_manager_assignment.model.Student;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(classes = {CourseCollectionRepository.class})
public class CourseCollectionRepositoryTest {

    @Autowired
    private CourseDao testObject;
    private StudentDao testStudentObject;

    Student student = new Student(231, "Aman", "23pinkpend@gmail.com", "Sweden");

    Collection<Course> courseList = new ArrayList<>();
    LocalDate date = LocalDate.of(2020, 12, 7);
    Course course = new Course(1, "Java", date, 1);

    @Test
    @DisplayName("Test context successfully setup")
    void context_loads() {
        assertFalse(testObject == null);
    }

    //Write your tests here
    public void test_createCourse() {
        testObject.createCourse(course.getCourseName(), course.getStartDate(), course.getWeekDuration());
        assertEquals(course, testObject.findAll());

    }

    @AfterEach
    void tearDown() {
        testObject.clear();
        CourseSequencer.setCourseSequencer(0);
    }
}
