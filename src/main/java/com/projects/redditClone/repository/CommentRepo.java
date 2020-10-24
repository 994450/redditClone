package com.projects.redditClone.repository;

import com.projects.redditClone.model.Comment;
import com.projects.redditClone.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepo  extends JpaRepository<Comment,Long> {
}
