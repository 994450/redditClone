package com.projects.redditClone.Service;


import com.projects.redditClone.Exception.SpringRedditException;
import com.projects.redditClone.dto.AuthenticationResponse;
import com.projects.redditClone.dto.LoginRequest;
import com.projects.redditClone.dto.RegisterRequest;
import com.projects.redditClone.model.NoticationEmail;
import com.projects.redditClone.model.User;
import com.projects.redditClone.model.VerficationToken;
import com.projects.redditClone.repository.UserRepo;
import com.projects.redditClone.repository.VerificationRepo;
import com.projects.redditClone.security.JwtProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthService {


    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;
    private final VerificationRepo verificationRepo;
    private final MailService mailService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    

    @Transactional
    public void signup(RegisterRequest registerRequest){
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setCreated(Instant.now());
        user.setEnabled(false);
        userRepo.save(user);

        generatateVerficationToken(user);
        String token = generatateVerficationToken(user);

        mailService.sendMail(new NoticationEmail("Activate email",user.getEmail(),"Thanks for signing up"
                +"link to activate"+"http://localhost:8080/api/auth/verfication/"+ token));
    }

    private String generatateVerficationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerficationToken verficationToken = new VerficationToken();
        verficationToken.setToken(token);
        verficationToken.setUser(user);
        verificationRepo.save(verficationToken);
        return token;
    }

    public void verifyAccount(String token) {
        Optional<VerficationToken> verficationToken = verificationRepo.findByToken(token);
        verficationToken.orElseThrow(() ->
                new SpringRedditException("Invalid Token"));
        fethUserAndEnable(verficationToken.get());
       }
    @Transactional
    private void fethUserAndEnable(VerficationToken verficationToken) {
        String username = verficationToken.getUser().getUsername();
        User user =userRepo.findByUsername(username).orElseThrow(() ->
                new SpringRedditException("User Not Found" + username));
        user.setEnabled(true);
        userRepo.save(user);
    }

    public AuthenticationResponse login (LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token =jwtProvider.generateToken(authenticate);
        return new AuthenticationResponse(token, loginRequest.getUsername());
    }
}
