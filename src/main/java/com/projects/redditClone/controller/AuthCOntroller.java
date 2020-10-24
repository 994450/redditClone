package com.projects.redditClone.controller;


import com.projects.redditClone.Service.AuthService;
import com.projects.redditClone.dto.AuthenticationResponse;
import com.projects.redditClone.dto.LoginRequest;
import com.projects.redditClone.dto.RegisterRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@AllArgsConstructor
public class AuthCOntroller {


    private final AuthService authService;

    @PostMapping("/signUp")
    public ResponseEntity<String> signup (@RequestBody RegisterRequest registerRequest){
        authService.signup(registerRequest);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping("verfication/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token){
        authService.verifyAccount(token);
        return new ResponseEntity<>("Account activatede",HttpStatus.OK);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

}
