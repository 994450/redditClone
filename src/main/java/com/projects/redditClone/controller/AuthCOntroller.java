package com.projects.redditClone.controller;


import com.projects.redditClone.Service.AuthService;
import com.projects.redditClone.dto.RegisterRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
