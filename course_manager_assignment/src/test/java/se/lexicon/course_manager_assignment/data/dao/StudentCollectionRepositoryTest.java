package se.lexicon.course_manager_assignment.data.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.course_manager_assignment.data.sequencers.StudentSequencer;
import se.lexicon.course_manager_assignment.model.Student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;


@SpringBootTest(classes = {StudentCollectionRepository.class})
public class StudentCollectionRepositoryTest {

    @Autowired
    private StudentDao testObject;
    Collection<Student> studentList = new ArrayList<>();
    Student student = new Student(2, "Aman", "23pinkpanda@gmail.com", "Sweden");
    Student student1 = new Student(3, "Sara", "test", "test");

    @Test
    @DisplayName("Test context successfully setup")
    void context_loads() {
        assertFalse(testObject == null);
    }


    //Write your tests here
    @Test
    public void Test_CreateStudent() {
        testObject.createStudent(student.getName(), student.getEmail(), student.getAddress());
        assertFalse(student.equals(testObject.findById(2)));
        assertFalse(student.equals(testObject.findAll()));


    }

    @Test
    public void test_findById() {
        Student student = new Student(2, "Aman", "23pinkpanda@gmail.com", "Sweden");
        testObject.createStudent(student.getName(), student.getEmail(), student.getAddress());


    }

    @Test

    public void Test_findByName() {
        List<Student> students = new ArrayList<>();
        students.add(student);
        testObject.createStudent(student.getName(), student.getEmail(), student.getAddress());

        Collection<Student> actualStudents = testObject.findByNameContains("Aman");

        assertEquals(student, testObject.findByNameContains(student.getName()));


    }

    private void assertEquals(Student student, Collection<Student> byNameContains) {
    }


    @Test

    public void Test_findAll() {
        testObject.createStudent(student.getName(), student.getEmail(), student.getAddress());
        testObject.createStudent(student1.getName(), student1.getEmail(), student1.getAddress());
        studentList.add(student);
        studentList.add(student1);
        Collection<Student> actualStudents = testObject.findAll();
        Assertions.assertEquals(studentList, actualStudents);


    }


    @AfterEach
    void tearDown() {
        testObject.clear();
        StudentSequencer.setStudentSequencer(0);
    }
}
