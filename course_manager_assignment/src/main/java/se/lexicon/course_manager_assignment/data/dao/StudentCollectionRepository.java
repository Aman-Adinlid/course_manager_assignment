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
        Student Student = new Student(name, email, address);
        if (Student.equals(null)) {
            throw new IllegalArgumentException(" obj is null");
        }
        for (Student student : students) {
            if (student.getId() == student.getId()) {
                return null;
            }
        }
        students.add(Student);
        return Student;
    }

    @Override
    public Student findByEmailIgnoreCase(String email) {
        Student student;
        for (Student stn : students) {
            if (stn.getEmail().equals(email)) {
                student = stn;
                return student;

            }
        }
        return null;
    }

    @Override
    public Collection<Student> findByNameContains(String name) {
        List<Student> studentList = new ArrayList<>();
        for (Student stn : students)
            if (stn.getName().contains(name)) {
                studentList.add(stn);
                return studentList;
            }
        return null;
    }

    @Override
    public Student findById(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException(" Not true");
        }
            Student student;
            for (Student stn : students) {
                if (stn.getId() == id) {
                    student = stn;
                    return student;
                }
            }
            return null;
        }

        @Override
        public Collection<Student> findAll () {
            List<Student> AllStudent = new ArrayList<>();
            for (Student stn : students) {
                AllStudent.add(stn);
            }
            return AllStudent;
        }

        @Override
        public boolean removeStudent (Student student){
            boolean remove = false;
            if(students.equals(null)) {
                throw new IllegalArgumentException("Null");
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
        public void clear () {
            this.students = new HashSet<>();
        }
    }
