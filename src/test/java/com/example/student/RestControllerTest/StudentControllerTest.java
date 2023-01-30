package com.example.student.RestControllerTest;

import com.example.student.Entity.Student;
import com.example.student.RestController.StudentController;
import com.example.student.Service.StudentServiceImplementation;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;

@WebMvcTest(StudentController.class)
public class StudentControllerTest {
    @MockBean
    private StudentServiceImplementation studentService;
    @Autowired
    protected MockMvc mockMvc;

    private Student student1;
    private Student student2;
    private Student student3;
    protected ObjectMapper mapper = new ObjectMapper();
    private final Set<Student> students = new HashSet<>();

    @BeforeEach
    void setup(){
        student1 = new Student(1L, "TestStudent1", "TestLastname1", "12345", 3, null);
        student2 = new Student(2L, "TestStudent2", "TestLastname2", "54321", 2, null);
        student3 = new Student( null,"TestStudent2", "TestLastname2", "54321", 2, null);
        students.add(student1);
        students.add(student2);
    }

    @Test
    void shouldReturnListOfStudents() throws Exception {
        when(studentService.findAll()).thenReturn(students);

        mockMvc.perform(get("/api/student"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnStudentByIdWhenExists() throws Exception{
        when(studentService.findById(student1.getId())).thenReturn(student1);

        mockMvc.perform(get("/api/student/{id}", student1.getId()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(student1.getId().intValue())))
                .andExpect(jsonPath("$.firstName", is(student1.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(student1.getLastName())))
                .andExpect(jsonPath("$.indexNumber", is(student1.getIndexNumber())))
                .andExpect(jsonPath("$.year", is(student1.getYear())));
    }

//    @Test
//    void shouldCreateNewStudent() throws Exception {
//        when(studentService.save(student3)).thenReturn(student3);
//
//        mockMvc.perform(post("/api/student")
//                        .accept(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(mapper.writeValueAsString(student3))
//                        .characterEncoding(StandardCharsets.UTF_8))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.id", is(student3.getId().intValue())))
//                .andExpect(jsonPath("$.firstName", is(student3.getFirstName())))
//                .andExpect(jsonPath("$.lastName", is(student3.getLastName())))
//                .andExpect(jsonPath("$.indexNumber", is(student3.getIndexNumber())))
//                .andExpect(jsonPath("$.year", is(student3.getYear())));
//    }

    @Test
    void shouldDeleteStudentIfExists() throws Exception{
        mockMvc.perform(delete("/api/student/{id}", student1.getId()))
                .andExpect(status().isOk());
    }
}
