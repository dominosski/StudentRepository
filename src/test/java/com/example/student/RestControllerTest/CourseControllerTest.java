package com.example.student.RestControllerTest;

import com.example.student.Entity.Course;
import com.example.student.RestController.CourseController;
import com.example.student.Service.CourseServiceImplementation;
import javassist.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CourseController.class)
public class CourseControllerTest {
    @MockBean
    private CourseServiceImplementation courseServiceImplementation;
    @Autowired
    protected MockMvc mockMvc;

    private Course course1;
    private Course course2;
    private Set<Course> courses;

    @BeforeEach
    void setup(){
        course1 = new Course(1L,"TestCourse1", null);
        course2 = new Course(2L,"TestCourse2", null);
        courses = new HashSet<>();
        courses.add(course1);
        courses.add(course2);
    }
    @Test
    public void shouldReturnListOfAllCourses() throws Exception {
        when(courseServiceImplementation.findAll()).thenReturn(courses);
        mockMvc.perform(get("/api/course"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void shouldReturnCourseByIdIfExists() throws Exception{
        when(courseServiceImplementation.findById(course1.getId())).thenReturn(course1);
        mockMvc.perform(get("/api/course/{id}", course1.getId()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(course1.getId().intValue())))
                .andExpect(jsonPath("$.courseName", is(course1.getCourseName())));
    }
    @Test
    public void shouldThrowExceptionWhenCourseDoesNotExist() throws NotFoundException {
        when(courseServiceImplementation.findById(3L)).thenThrow(NotFoundException.class);
    }
    @Test
    public void shouldRemoveCourseIfExists() throws Exception {
        mockMvc.perform(delete("/api/course/{id}", course1.getId()))
                .andExpect(status().isOk());
    }
}
