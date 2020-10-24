package com.projects.redditClone.repository;

import com.projects.redditClone.model.Post;
import com.projects.redditClone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo   extends JpaRepository<User,Long> {
}
