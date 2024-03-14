package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.Clinics;
import com.example.demo.model.Doctor;
import com.example.demo.model.ERole;
import com.example.demo.model.Patients;
import com.example.demo.model.Specializations;
import com.example.demo.model.User;
import com.example.demo.repository.DoctorRepository;
import com.example.demo.repository.PatientsRepository;
import com.example.demo.repository.UserRepository;

@Service
public class UserService   {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PatientsRepository patientsRepository;
	
	@Autowired
	DoctorRepository doctorRepository;
	
	@Autowired
	ClinicsService clinicsService;
	
	@Autowired
	SpecializationsService specializationsService;
	
	public void addUser(User user) {
		userRepository.save(user);
	}
//	set status  = 0 : khoá, 1 : hoat dong
	public void setStatus(int status, int id) {
		userRepository.StatusAndId(status, id);
	}
	
	public void setStatus(User user) {
		int status=0;
		int id = user.getId();
		if(user.getStatus()==status) {
			status =1;
		}
		userRepository.StatusAndId(status, id);
	}
	
	public User setStatus(int userId) {
		User user = getUserById(userId);
		if(user.getStatus()==0) {
			user.setStatus(1) ;
		}else {
			user.setStatus(0) ;
		}
		return userRepository.saveAndFlush(user);
	}
	
	public User getUserById(int id) {
		User user = userRepository.findById(id).get();
		return user;
	}
	
	public User getUserByEmail(String email) {
		User user = userRepository.findByEmail(email).get();
		return user;
	}
	
	public Optional<User>  getUserByEmailOptional(String email) {
		Optional<User> user = userRepository.findByEmail(email);
		return user;
	}
	
	public List<User> lisUsers() {
		List<User> list = new ArrayList<User>();
		list = userRepository.findAll();
		return list;
	}
	public Boolean AddUser(User user) {
		Patients patients = new Patients();
		Doctor doctor = new Doctor();
		if(!isExist(user.getEmail())) {
			User u = userRepository.save(user);
			if(user.geteRole().equals(ERole.ROLE_USER))
			{
				patients.setUser(u);
				patientsRepository.save(patients);
			}else if (user.geteRole().equals(ERole.ROLE_DOCTOR)) {
				doctor.setUser(u);
				doctorRepository.save(doctor);
			}
			return true;
		};
		return false;
	}
	
	public Boolean AddUser(UserDTO userDTO) {
		Patients patients = new Patients();
		Doctor doctor = new Doctor();
		User user = new User(userDTO);
		if(!isExist(userDTO.getEmail())) {
			User u = userRepository.save(user);
			if(user.geteRole().equals(ERole.ROLE_USER))
			{
				patients.setUser(u);
				patientsRepository.save(patients);
			}else if (user.geteRole().equals(ERole.ROLE_DOCTOR)) {
				Clinics clinics = clinicsService.findByIdClinics(userDTO.getClinicsId());
				Specializations specializations = specializationsService.findById(userDTO.getSpecializationsId());
				doctor.setUser(u);
				doctor.setClinics(clinics);
				doctor.setSpecializations(specializations);
				doctor.setStatus(userDTO.getStatus());
				doctorRepository.save(doctor);
			}

			return true;
		};
		return false;
	}
	
	public Boolean isExist(String email) {
//		true : tai khoan ton tai
		Boolean check= false;
		List<User> list = userRepository.findAll();
		for (User user : list) {
			if (user.getEmail().equals(email)) {
				check=true;
			}
		}
		return check;
	}
	
//	set password 
	public void setPass(String pass, String email) {
		User user = userRepository.findByEmail(email).get();
		user.setPassword(pass);
		userRepository.saveAndFlush(user);
	}

}