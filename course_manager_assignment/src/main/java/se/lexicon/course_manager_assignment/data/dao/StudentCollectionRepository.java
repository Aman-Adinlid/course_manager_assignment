package se.lexicon.course_manager_assignment.data.dao;

import se.lexicon.course_manager_assignment.data.sequencers.StudentSequencer;
import se.lexicon.course_manager_assignment.model.Student;

import java.util.*;

public class StudentCollectionRepository implements StudentDao {

    private Collection<Student> students;

    public StudentCollectionRepository(Collection<Student> students) {
        this.students = students;
    }

    @Override
    public Student createStudent(String name, String email, String address) {
        int id = StudentSequencer.nextStudentId();
        Student student = new Student(id, name, email, address);

        students.add(student);
        return student;
    }

    @Override
    public Student findByEmailIgnoreCase(String email) {
        if (email.equals(null)) {
            return null;
        }
        Student student = null;

        for (Student student1 : students) {
            if (student.getEmail().equalsIgnoreCase(email)) {
                student = student1;
                return student;
            }
        }
        return null;
    }

    @Override
    public Collection<Student> findByNameContains(String name) {
        List<Student> studentList = new ArrayList<>();
        if (name.equals(null)) {
            return null;
        }
        for (Student student : students)
            if (student.getName().contains(name)) {
                studentList.add(student);
                return studentList;
            }
        return null;
    }

    @Override
    public Student findById(int id) {
        Student student = new Student();

        if (id <= 0) {
            return null;
        }
        for (Student student1 : students) {
            if (student1.getId() == id) {
                student = student1;
                return student;
            }
        }
        return null;
    }

    @Override
    public Collection<Student> findAll() {
        List<Student> AllStudent = new ArrayList<>();
        for (Student student : students) {
            AllStudent.add(student);
        }
        return AllStudent;
    }

    @Override
    public boolean removeStudent(Student student) {
        boolean remove = false;
        if (students.equals(null)) {
            return false;
        }
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student result = iterator.next();
            if (result.equals(student)) {
                iterator.remove();
                remove = true;
            }
        }
        return remove;


    }

    @Override
    public void clear() {
        this.students = new HashSet<>();
    }
}
