package com.example.demo.dto;

import com.example.demo.model.ERole;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class UserDTO {

		private int id;
		private String address;
		private String description;
		private String email;
		private String fullName;
		private String image;
		private String password;
		private String phoneNumber;
		private int status;
//		status = 0: lock, =1 : active
		private int gender;
//		gender = 0: nu, =1 : nam
		private int clinicsId;
	    private int specializationsId;
	    
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

		public int getClinicsId() {
			return clinicsId;
		}

		public void setClinicsId(int clinicsId) {
			this.clinicsId = clinicsId;
		}

		public int getSpecializationsId() {
			return specializationsId;
		}

		public void setSpecializationsId(int specializationsId) {
			this.specializationsId = specializationsId;
		}
		
	


}
