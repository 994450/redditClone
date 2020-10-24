package com.projects.redditClone.repository;

import com.projects.redditClone.model.Subreddit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubredditRepo  extends JpaRepository<Subreddit, Long> {
}
