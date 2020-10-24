package com.projects.redditClone.Service;


import com.projects.redditClone.dto.RegisterRequest;
import com.projects.redditClone.model.NoticationEmail;
import com.projects.redditClone.model.User;
import com.projects.redditClone.model.VerficationToken;
import com.projects.redditClone.repository.UserRepo;
import com.projects.redditClone.repository.VerificationRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthService {


    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;
    private final VerificationRepo verificationRepo;
    private final MailService mailService;

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
                +"link to activate"+"http://localhost:8080/api/auth/accountVerification/"+ token));
    }

    private String generatateVerficationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerficationToken verficationToken = new VerficationToken();
        verficationToken.setToken(token);
        verficationToken.setUser(user);
        verificationRepo.save(verficationToken);
        return token;
    }
}
