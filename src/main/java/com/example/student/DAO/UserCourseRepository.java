package com.example.student.DAO;

import com.example.student.Entity.UserCourse;
import com.example.student.Entity.UserCourseId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCourseRepository extends JpaRepository<UserCourse, UserCourseId> {
}
