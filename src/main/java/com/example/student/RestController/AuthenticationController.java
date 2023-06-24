package com.example.student.RestController;

import com.example.student.Request.AuthenticationRequest;
import com.example.student.Request.RegisterRequest;
import com.example.student.Response.AuthenticationReponse;
import com.example.student.Service.AuthenticationService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Data
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public AuthenticationReponse register(@RequestBody RegisterRequest registerRequest){
        return authenticationService.register(registerRequest);
    }

    @PostMapping("/login")
    public AuthenticationReponse login(@RequestBody AuthenticationRequest authenticationRequest){
        return authenticationService.authenticate(authenticationRequest);
    }
}
