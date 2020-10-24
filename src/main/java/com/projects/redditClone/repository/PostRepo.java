package com.projects.redditClone.repository;


import com.projects.redditClone.model.Post;
import javafx.geometry.Pos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepo extends JpaRepository<Post,Long> {
}
