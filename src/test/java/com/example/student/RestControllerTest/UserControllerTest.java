package com.example.student.RestControllerTest;

import com.example.student.Entity.User;
import com.example.student.RestController.UserController;
import com.example.student.Service.UserServiceImplementation;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;

@WebMvcTest(UserController.class)
public class UserControllerTest {
    @MockBean
    private UserServiceImplementation userService;

    @Autowired
    protected MockMvc mockMvc;

    private User user1;
    private User user2;
    protected ObjectMapper mapper = new ObjectMapper();
    private final Set<User> users = new HashSet<>();

    @BeforeEach
    void setup(){
        user1 = new User(1L, "TestUser1", "TestLastname1", "12345", 3, null, null);
        user2 = new User(2L, "TestUser2", "TestLastname2", "54321", 2, null, null);
        users.add(user1);
        users.add(user2);
    }

    @Test
    void shouldReturnListOfUsers() throws Exception {
        when(userService.findAll()).thenReturn(users);

        mockMvc.perform(get("/api/user"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnUserByIdWhenExists() throws Exception{
        when(userService.findById(user1.getId())).thenReturn(user1);

        mockMvc.perform(get("/api/user/{id}", user1.getId()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(user1.getId().intValue())))
                .andExpect(jsonPath("$.firstName", is(user1.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(user1.getLastName())))
                .andExpect(jsonPath("$.indexNumber", is(user1.getIndexNumber())))
                .andExpect(jsonPath("$.year", is(user1.getYear())));
    }

//    @Test
//    void shouldCreateNewStudent() throws Exception {
//        when(studentService.save(user3)).thenReturn(user2);
//
//        mockMvc.perform(post("/api/student")
//                        .accept(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(mapper.writeValueAsString(user3)))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.firstName", is(user3.getFirstName())))
//                .andExpect(jsonPath("$.lastName", is(user3.getLastName())))
//                .andExpect(jsonPath("$.indexNumber", is(user3.getIndexNumber())))
//                .andExpect(jsonPath("$.year", is(user3.getYear())));
//    }

    @Test
    void shouldDeleteUserIfExists() throws Exception{
        mockMvc.perform(delete("/api/user/{id}", user1.getId()))
                .andExpect(status().isOk());
    }
}
