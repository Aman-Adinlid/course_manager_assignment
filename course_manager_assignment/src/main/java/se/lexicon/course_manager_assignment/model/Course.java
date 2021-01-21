package se.lexicon.course_manager_assignment.model;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Objects;

public class Course {

    private int Id;
    private String courseName;
    private LocalDate startDate;
    private int weekDuration;
    private Collection <Student> students;


    public Course() {
    }

    public Course (String courseName, LocalDate startDate, int weekDuration) {
        this.courseName = courseName;
        startDate = startDate;
        this.weekDuration = weekDuration;
    }

    public Course(Integer id, String courseName, LocalDate startDate, int weekDuration) {
        this.Id = id;
        this.courseName = courseName;
        startDate = startDate;
        this.weekDuration = weekDuration;

    }

    public Course(String courseName, LocalDate startDate, int weekDuration, Collection<Student> students) {
        this.courseName = courseName;
        startDate = startDate;
        this.weekDuration = weekDuration;
        this.students = students;
    }
    public Course(int Id, String courseName, LocalDate startDate, int weekDuration, Collection<Student> students) {
        Id = Id;
        this.courseName = courseName;
        this.startDate = startDate;
        this.weekDuration = weekDuration;
        this.students = students;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String course) {
        this.courseName = course;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public int getWeekDuration() {
        return weekDuration;
    }

    public void setWeekDuration(int weekDuration) {
        this.weekDuration = weekDuration;
    }

    public Collection<Student> getStudents() {
        return students;
    }

    public void setStudents(Collection<Student> students) {
        this.students = students;
    }

    public boolean enrollStudent(Student student){
        return true;
    }

    public boolean unenrollStudent(Student student){
        return true;
    }


    @Override
    public int hashCode() {
        return Objects.hash(Id, courseName, startDate, weekDuration, students);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Id == course.Id && weekDuration == course.weekDuration && Objects.equals(courseName, course.courseName) && Objects.equals(startDate, course.startDate) && Objects.equals(students, course.students);
    }

    @Override
    public String toString() {
        return "Course{" +
                "Id=" + Id +
                ", courseName='" + courseName + '\'' +
                ", startDate=" + startDate +
                ", weekDuration=" + weekDuration +
                ", students=" + students +
                '}';
    }
}
