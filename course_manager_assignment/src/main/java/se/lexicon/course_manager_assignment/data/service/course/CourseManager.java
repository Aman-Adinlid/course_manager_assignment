package se.lexicon.course_manager_assignment.data.service.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.course_manager_assignment.data.dao.CourseDao;
import se.lexicon.course_manager_assignment.data.dao.StudentDao;
import se.lexicon.course_manager_assignment.data.service.converter.Converters;
import se.lexicon.course_manager_assignment.dto.forms.CreateCourseForm;
import se.lexicon.course_manager_assignment.dto.forms.UpdateCourseForm;
import se.lexicon.course_manager_assignment.dto.views.CourseView;
import se.lexicon.course_manager_assignment.model.Course;


import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Service
public class CourseManager implements CourseService {

    private final CourseDao courseDao;
    private final StudentDao studentDao;
    private final Converters converters;

    @Autowired
    public CourseManager(CourseDao courseDao, StudentDao studentDao, Converters converters) {
        this.courseDao = courseDao;
        this.studentDao = studentDao;
        this.converters = converters;
    }

    @Override
    public CourseView create(CreateCourseForm form) {

        return new CourseView(form.getId(), form.getCourseName(), form.getStartDate(), form.getWeekDuration(), converters.studentsToStudentViews(studentDao.findAll()));
    }

    @Override
    public CourseView update(UpdateCourseForm form) {
        Course update = courseDao.findById(form.getId());
        update.setCourseName(form.getCourseName());
        update.setStartDate(form.getStartDate());
        update.setWeekDuration(form.getWeekDuration());

        return converters.courseToCourseView(update);
    }

    @Override
    public List<CourseView> searchByCourseName(String courseName) {
        Collection<Course> searchByCourseName = courseDao.findByNameContains(courseName);
        return converters.coursesToCourseViews(searchByCourseName);
    }

    @Override
    public List<CourseView> searchByDateBefore(LocalDate end) {
        Collection<Course> searchByDateBefore = courseDao.findByDateBefore(end);
        return converters.coursesToCourseViews(searchByDateBefore);
    }

    @Override
    public List<CourseView> searchByDateAfter(LocalDate start) {
        Collection<Course> searchByDateAfter = courseDao.findByDateAfter(start);
        return converters.coursesToCourseViews(searchByDateAfter);
    }

    @Override
    public boolean addStudentToCourse(int courseId, int studentId) {
        boolean addStudentToCourse = courseDao.findById(courseId).enrollStudent(studentDao.findById(studentId));
        return addStudentToCourse;
    }

    @Override
    public boolean removeStudentFromCourse(int courseId, int studentId) {
        boolean removeStudentFromCourse = courseDao.findById(courseId).unenrollStudent(studentDao.findById(studentId));
        return removeStudentFromCourse;

    }

    @Override
    public CourseView findById(int id) {
        Course findById = courseDao.findById(id);
        return converters.courseToCourseView(findById);

    }

    @Override
    public List<CourseView> findAll() {
        Collection<Course> findAll = courseDao.findAll();
        return converters.coursesToCourseViews(findAll);
    }


    @Override
    public List<CourseView> findByStudentId(int studentId) {
        Collection<Course> findByStudentId = courseDao.findByStudentId(studentId);
        return converters.coursesToCourseViews(findByStudentId);
    }

    @Override
    public boolean deleteCourse(int id) {
        boolean deleteCourse = courseDao.removeCourse(courseDao.findById(id));
        return deleteCourse;

    }
}
