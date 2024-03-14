package com.example.demo.dto;

import java.util.List;

import com.example.demo.model.Clinics;
import com.example.demo.model.Doctor;

public class InformationDTO {
	List<Doctor> doctors;
	List<Clinics> clinics;
	public List<Doctor> getDoctors() {
		return doctors;
	}
	public void setDoctors(List<Doctor> doctors) {
		this.doctors = doctors;
	}
	public List<Clinics> getClinics() {
		return clinics;
	}
	public void setClinics(List<Clinics> clinics) {
		this.clinics = clinics;
	}
	
	
}
