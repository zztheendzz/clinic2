package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Clinics;
import com.example.demo.model.Posts;
import com.example.demo.model.Specializations;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Repository
	public interface ClinicsRepository  extends JpaRepository<Clinics, Integer> {

	@Query("SELECT u FROM Clinics u WHERE u.id = :id")
	Optional<Clinics> findById(@Param("id") int id);
	

//	@Query("SELECT u FROM Clinics u WHERE u.address LIKE '%:address%'")
//	@Query("SELECT u FROM Clinics u WHERE u.address LIKE CONCAT('%',:address,'%')")
//	@Query("SELECT u FROM Clinics u WHERE u.address LIKE '%' || :lastname || '%'  ")
//	@Query("SELECT u FROM Clinics u WHERE u.address LIKE %:address%")
	@Query("SELECT * FROM Clinics WHERE address LIKE %:address%")
	Optional<List<Clinics>> findByAddressLike(String address);
	
}
