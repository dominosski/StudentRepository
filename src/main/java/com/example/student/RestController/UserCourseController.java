package com.example.student.RestController;

import com.example.student.Entity.UserCourse;
import com.example.student.Service.UserCourseService;
import javassist.NotFoundException;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

@RestController
@Data
@RequestMapping("/api/usercourse")
public class UserCourseController {
    private final UserCourseService userCourseService;

    @PutMapping("/user/{userId}/course/{courseId}/addGrade")
    public UserCourse addGrade(@PathVariable Long userId, @PathVariable Long courseId, @RequestBody UserCourse grade) throws NotFoundException {
        return userCourseService.addGradeToUserCourse(userId, courseId, grade);
    }
}
