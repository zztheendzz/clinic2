package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Doctor;
import com.example.demo.model.Places;
import com.example.demo.model.Schedules;

@Repository
public interface SchedulesRepository  extends JpaRepository<Schedules, Integer> {

	@Query("SELECT u FROM Schedules u WHERE u.id = :id")
	Optional<Schedules> findById(@Param("id") int id);
//	Optional<User> findByEmail( String email);
//	@Query("SELECT u FROM Schedules u WHERE u.doctor_id = :doctor_id")
//	List<Schedules> findByDoctorId(@Param("doctor_id") Integer doctor_id);
	
	@Modifying
    @Query(value="update Schedules set status= :status And set description= :description  where id = :id")
    Optional<Schedules> StatusAndDescriptionAndId(@Param("status")int status,@Param("description") String description,@Param("id")int id);

	@Query("SELECT u FROM Schedules u WHERE u.doctorId = :id")
	Optional<List<Schedules>> findByDoctorId(@Param("id") int id);
	
	@Query("SELECT u FROM Schedules u WHERE u.patientsId = :id")
	Optional<List<Schedules>> findByPatientsId(@Param("id") int id);
}
