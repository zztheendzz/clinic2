package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Sequelizemeta;
import com.example.demo.model.Supporterlogs;

@Repository
public interface SupporterlogsRepository  extends JpaRepository<Supporterlogs, Integer> {

	@Query("SELECT u FROM Supporterlogs u WHERE u.id = :id")
	Optional<Supporterlogs> findById(@Param("id") int id);
//	Optional<User> findByEmail( String email);
}
