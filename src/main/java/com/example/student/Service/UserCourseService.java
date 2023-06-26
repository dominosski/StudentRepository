package com.example.student.Service;

import com.example.student.DAO.UserCourseRepository;
import com.example.student.Entity.Course;
import com.example.student.Entity.User;
import com.example.student.Entity.UserCourse;
import com.example.student.Entity.UserCourseId;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserCourseService {
    private final UserCourseRepository userCourseRepository;
    private final UserServiceImplementation userServiceImplementation;
    private final CourseServiceImplementation courseServiceImplementation;

    public UserCourse addGradeToUserCourse(Long userId, Long courseId, UserCourse grade) throws NotFoundException {
        User user = userServiceImplementation.findById(userId);
        Course course = courseServiceImplementation.findById(courseId);
        UserCourseId userCourseId = new UserCourseId(user, course);
        UserCourse userCourse = userCourseRepository.findById(userCourseId)
                .orElseThrow(() -> new NotFoundException("UserCourse not found"));
        userCourse.setGrade(grade.getGrade());

        return userCourseRepository.save(userCourse);
    }

    public UserCourse deleteGradeFromUser(Long userId, Long courseId, Long gradeId) throws NotFoundException {
        User user = userServiceImplementation.findById(userId);
        Course course = courseServiceImplementation.findById(courseId);
        UserCourseId userCourseId = new UserCourseId(user, course);
        UserCourse userCourse = userCourseRepository.findById(userCourseId)
                .orElseThrow(() -> new NotFoundException("UserCourse not found"));
        userCourse.setGrade(null);
        return userCourseRepository.save(userCourse);
    }
}
