package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Comments;
import com.example.demo.model.Posts;

@Repository
public interface CommentsRepository  extends JpaRepository<Comments, Integer> {
	@Query("SELECT u FROM Comments u WHERE u.id = :id")
	Optional<Comments> findById(@Param("id") int id);


}
