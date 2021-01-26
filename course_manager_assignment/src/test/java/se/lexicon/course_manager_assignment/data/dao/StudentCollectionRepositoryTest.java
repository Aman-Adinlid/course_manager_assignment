package se.lexicon.course_manager_assignment.data.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.course_manager_assignment.data.sequencers.StudentSequencer;
import se.lexicon.course_manager_assignment.model.Student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;



@SpringBootTest(classes = {StudentCollectionRepository.class})
public class StudentCollectionRepositoryTest {

    @Autowired
    private StudentDao testObject;
    Collection<Student> studentList = new ArrayList<>();
    Student student = new Student(2, "Aman", "23pinkpanda@gmail.com", "Sweden");

    @Test
    @DisplayName("Test context successfully setup")
    void context_loads() {
        assertFalse(testObject == null);
    }



    //Write your tests here
    @Test
    public void Test_CreateStudent() {
        Student student = new Student(2, "Aman", "23pinkpanda@gmail.com", "Sweden");
        testObject.createStudent(student.getName(), student.getEmail(), student.getAddress());


    }

    @Test
    public void test_findById() {
        Student expectedStudent = student;
        testObject.createStudent(student.getName(), student.getEmail(), student.getAddress());
        Student actualStudent = testObject.findById(student.getId());

    }

    @Test

    public void Test_findByName() {
        Student student = new Student("Aman", "test", "Sweden");
        testObject.createStudent(student.getName(), student.getEmail(), student.getAddress());
        assertEquals(student, testObject.findByEmailIgnoreCase(student.getEmail()));
    }

     @Test

     public void Test_findAll() {
         List<Student> findAllStudents = new ArrayList<>();
         Student student = new Student(2, "Aman", "23pinkpanda@gmail.com", "Sweden");
          findAllStudents.add(student);
          testObject.createStudent(student.getName(), student.getEmail(), student.getAddress());



     }


    @AfterEach
    void tearDown() {
        testObject.clear();
        StudentSequencer.setStudentSequencer(0);
    }
}
