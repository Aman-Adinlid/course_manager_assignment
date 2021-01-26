package se.lexicon.course_manager_assignment.data.service.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.course_manager_assignment.data.dao.CourseDao;
import se.lexicon.course_manager_assignment.data.dao.StudentDao;
import se.lexicon.course_manager_assignment.data.service.converter.Converters;
import se.lexicon.course_manager_assignment.dto.forms.CreateStudentForm;
import se.lexicon.course_manager_assignment.dto.forms.UpdateStudentForm;
import se.lexicon.course_manager_assignment.dto.views.CourseView;
import se.lexicon.course_manager_assignment.dto.views.StudentView;
import se.lexicon.course_manager_assignment.model.Course;
import se.lexicon.course_manager_assignment.model.Student;


import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;


@Service
public class StudentManager implements StudentService {

    private final StudentDao studentDao;
    private final CourseDao courseDao;
    private final Converters converters;

    @Autowired
    public StudentManager(StudentDao studentDao, CourseDao courseDao, Converters converters) {
        this.studentDao = studentDao;
        this.courseDao = courseDao;
        this.converters = converters;
    }

    @Override
    public StudentView create(CreateStudentForm form) {
        return new StudentView(form.getId(), form.getName(), form.getEmail(), form.getAddress());
    }

    @Override
    public StudentView update(UpdateStudentForm form) {
        Student update = studentDao.findById(form.getId());
        update.setName(form.getName());
        update.setAddress(form.getAddress());
        update.setEmail(form.getEmail());
        return converters.studentToStudentView(update);

    }

    @Override
    public StudentView findById(int id) {
        Student findById = studentDao.findById(id);
        return converters.studentToStudentView(findById);
    }

    @Override
    public StudentView searchByEmail(String email) {
        Student searchByEmail = studentDao.findByEmailIgnoreCase(email);
        return converters.studentToStudentView(searchByEmail);

    }

    @Override
    public List<StudentView> searchByName(String name) {
        Collection<Student> searchByName = studentDao.findByNameContains(name);
        return converters.studentsToStudentViews(searchByName);
    }

    @Override
    public List<StudentView> findAll() {
        Collection<Student> findAll = studentDao.findAll();
        return converters.studentsToStudentViews(findAll);

    }

    @Override
    public boolean deleteStudent(int id) {
        boolean deleteStudent = studentDao.removeStudent(studentDao.findById(id));
        return deleteStudent;
    }
}
