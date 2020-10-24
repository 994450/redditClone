package com.projects.redditClone.repository;

import com.projects.redditClone.model.Post;
import com.projects.redditClone.model.VerficationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerificationRepo extends JpaRepository<VerficationToken,Long> {
     Optional<VerficationToken> findByToken(String Token);
}
