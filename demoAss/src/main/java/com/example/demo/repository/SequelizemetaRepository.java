package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Schedules;
import com.example.demo.model.Sequelizemeta;

@Repository
public interface SequelizemetaRepository  extends JpaRepository<Sequelizemeta, Integer> {

	@Query("SELECT u FROM Sequelizemeta u WHERE u.id = :id")
	Optional<Sequelizemeta> findById(@Param("id") int id);
//	Optional<User> findByEmail( String email);
}