package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;

@Repository
public interface UserRepository  extends JpaRepository<User, Integer> {

	@Query("SELECT u FROM User u WHERE u.email = :email")
	Optional<User> findByEmail(@Param("email") String email);

    @Query(value="update User u set u.status= :status where u.id = :id")
    void StatusAndId(@Param("status")int status,@Param("id")int id);
    
    @Query(value="SELECT u FROM User u WHERE u.id = :id")
    Optional<User> findById(@Param("id")int id);
    
}