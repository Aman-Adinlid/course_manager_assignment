package se.lexicon.course_manager_assignment.data.dao;


import se.lexicon.course_manager_assignment.data.sequencers.StudentSequencer;
import se.lexicon.course_manager_assignment.model.Course;
import se.lexicon.course_manager_assignment.model.Student;

import java.time.LocalDate;
import java.util.*;


public class CourseCollectionRepository implements CourseDao {

    private Collection<Course> courses;


    public CourseCollectionRepository(Collection<Course> courses) {
        this.courses = courses;
    }

    @Override
    public Course createCourse(String courseName, LocalDate startDate, int weekDuration) {
        int Id = StudentSequencer.nextStudentId();
        Course course = new Course(Id, courseName, startDate, weekDuration);
        if (course.equals(null)) {
            throw new IllegalArgumentException("Obj is null");
        }
        for (Course course1 : courses) {
            if (course1.getId() == course1.getId()) {
                return null;
            }
        }
        return course;
    }

    @Override
    public Course findById(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException(" No");
        }
        Course course = new Course();
        for (Course course1 : courses) {
            if (course1.getId() == id) {
                course = course1;
            }
        }
        return course;
    }


    @Override
    public Collection<Course> findByNameContains(String name) {
        List<Course> courseList = new ArrayList<>();
        for (Course course : courses)
            if (course.getCourseName().contains(name)) {
                courseList.add(course);
                return courseList;
            }
        return null;
    }

    @Override
    public Collection<Course> findByDateBefore(LocalDate end) {
        return null;
    }

    @Override
    public Collection<Course> findByDateAfter(LocalDate start) {
        return null;
    }

    @Override
    public Collection<Course> findAll() {
        List<Course> AllCourses = new ArrayList<>();
        for (Course course : courses) {
            AllCourses.add(course);
        }
        return AllCourses;
    }


    @Override
    public Collection<Course> findByStudentId(int studentId) {
        if (studentId <= 0) {
            throw new IllegalArgumentException(" Not true");
        }
        Student student = new Student();
        List<Course> courses1 = new ArrayList<>();
        for (Course course : courses) {
            for (Student stn : course.getStudents()) {
                student = stn;
                if (student.getId() == studentId) {
                    courses1.add(course);
                }
            }
        }
        return courses1;
    }

    @Override
    public boolean removeCourse(Course course) {
        boolean remove = false;
        if (course.equals(null)) {
            throw new IllegalArgumentException("Null");
        }
        Iterator<Course> iterator = courses.iterator();
        while (iterator.hasNext()) {
            Course result = iterator.next();
            if (result.equals(course)) {
                iterator.remove();
                remove = true;
            }
        }
        return remove;


    }

    @Override
    public void clear() {
        this.courses = new HashSet<>();
    }
}
