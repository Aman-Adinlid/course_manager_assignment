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
            return null;
        }
        for (Course course1 : courses) {
            if (course1.getId() == course1.getId()) {
                return null;
            }
        }
        courses.add(course);
        return course;
    }

    @Override
    public Course findById(int id) {
        Course course = new Course();

        if (id <= 0) {
            return null;
        }
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

        if (name.equals(null)) {
            return null;
        }
        for (Course course : courses) {
            if (course.getCourseName().contains(name)) {
                courseList.add(course);
            }
        }
        return courseList;
    }

    @Override
    public Collection<Course> findByDateBefore(LocalDate end) {
        List<Course> result = new ArrayList<>();
        if (end.equals(null)) {
            return null;
        }
        for (Course course : courses) {
            if (course.getStartDate().plusWeeks(course.getWeekDuration()).isBefore(end)) {
                result.add(course);
            }
        }
        return result;
    }

    @Override
    public Collection<Course> findByDateAfter(LocalDate start) {
        List<Course> result = new ArrayList<>();
        if (start.equals(null)) {
            return null;
        }
        for (Course course : courses) {
            if (course.getStartDate().isAfter(start)) {
                result.add(course);
            }
        }
        return result;
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
        Student student = new Student();
        List<Course> theCourseArray = new ArrayList<>();
        if (studentId <= 0) {
            return null;
        }

        for (Course course : courses) {
            for (Student student1 : course.getStudents()) {
                student = student1;
                if (student.getId() == studentId) {
                    theCourseArray.add(course);
                }
            }
        }
        return theCourseArray;
    }

    @Override
    public boolean removeCourse(Course course) {
        boolean remove = false;
        if (course.equals(null)) {
            return false;
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
