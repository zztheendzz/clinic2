package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Extrainfos;
import com.example.demo.model.Places;

@Repository
public interface PlacesRepository  extends JpaRepository<Places, Integer> {

	@Query("SELECT u FROM Places u WHERE u.id = :id")
	Optional<Places> findById(@Param("id") int id);
//	Optional<User> findByEmail( String email);
}