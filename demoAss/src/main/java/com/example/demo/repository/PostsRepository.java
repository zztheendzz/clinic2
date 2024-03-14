package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Places;
import com.example.demo.model.Posts;


@Repository
public interface PostsRepository  extends JpaRepository<Posts, Integer> {
	@Query("SELECT u FROM Posts u WHERE u.id = :id")
	Optional<Posts> findById(@Param("id") int id);
}