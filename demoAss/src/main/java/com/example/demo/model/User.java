package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.dto.UserDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "User")
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "address")
	private String address;

	@Column(name = "description")
	private String description;

	@Column(name = "email")
	private String email;

	@Column(name = "full_name")
	private String fullName;

	@Column(name = "image")
	private String image;

	@Column(name = "password")
	private String password;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "status")
	private int status;
//	status = 0: lock, =1 : active
	
	@Column(name = "gender")
	private int gender;
//	gender = 0: nu, =1 : nam

	@Enumerated(EnumType.STRING)
	private ERole eRole;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public ERole geteRole() {
		return eRole;
	}

	public void seteRole(ERole eRole) {
		this.eRole = eRole;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}
	
	public User( ) {
	}

	public User(UserDTO userDTO) {

		this.address = userDTO.getAddress();
		this.description = userDTO.getDescription();
		this.email = userDTO.getEmail();
		this.fullName = userDTO.getFullName();
		this.image = userDTO.getImage();
		this.password = userDTO.getPassword();
		this.phoneNumber = userDTO.getPhoneNumber();
		this.status = userDTO.getStatus();
		this.gender = userDTO.getGender();
		this.eRole = userDTO.geteRole();
	}
	
	
}
