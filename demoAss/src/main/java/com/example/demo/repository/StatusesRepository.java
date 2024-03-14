package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Specializations;
import com.example.demo.model.Statuses;


@Repository
public interface StatusesRepository  extends JpaRepository<Statuses, Integer> {

	@Query("SELECT u FROM Statuses u WHERE u.id = :id")
	Optional<Statuses> findById(@Param("id") int id);
	
//	Optional<User> findByEmail( String email);
}