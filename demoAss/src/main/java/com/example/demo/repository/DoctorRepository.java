package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Doctor;
import com.example.demo.model.User;

@Repository
public interface DoctorRepository  extends JpaRepository<Doctor, Integer> {
	@Query("SELECT u FROM Doctor u WHERE u.id = :id")
	Optional<Doctor> findById(@Param("id") Integer id);

	@Query("SELECT u FROM Doctor u WHERE u.status = :status")
	List<Doctor> findByStatus(@Param("status") Integer status);
	
	@Modifying
    @Query(value="update Doctor set status= :status where id = :id", nativeQuery=true)
    void updateDoctorById(@Param("status")int status,@Param("id")int id);
	

}
