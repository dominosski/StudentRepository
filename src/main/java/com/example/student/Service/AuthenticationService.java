package com.example.student.Service;

import com.example.student.Config.JwtService;
import com.example.student.DAO.UserRepository;
import com.example.student.Entity.Role;
import com.example.student.Entity.User;
import com.example.student.Request.AuthenticationRequest;
import com.example.student.Request.RegisterRequest;
import com.example.student.Response.AuthenticationReponse;
import lombok.Data;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Data
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationReponse register(RegisterRequest registerRequest){
        User user = User.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.ROLE_USER)
                .build();
        userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationReponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationReponse authenticate(AuthenticationRequest authenticationRequest){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()
                )
        );

        User user = userRepository.findByEmail(authenticationRequest.getEmail()).orElseThrow();
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationReponse.builder()
                .token(jwtToken)
                .build();
    }
}
