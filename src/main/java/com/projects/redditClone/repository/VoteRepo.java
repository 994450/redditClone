package com.projects.redditClone.repository;

import com.projects.redditClone.model.Post;
import com.projects.redditClone.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepo  extends JpaRepository<Vote,Long> {
}
