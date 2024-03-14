package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Sequelizemeta;
import com.example.demo.model.Specializations;

@Repository
public interface SpecializationsRepository  extends JpaRepository<Specializations, Integer> {

	@Query("SELECT u FROM Specializations u WHERE u.id = :id")
	Optional<Specializations> findById(@Param("id") int id);
//	Optional<User> findByEmail( String email);
	@Query("SELECT * FROM Specializations u like %keySearch%")
	List<Specializations> findKeySearchLst(@Param("keySearch") String keySearch);

}