package com.example.demo.dto;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.model.Doctor;
import com.example.demo.model.Schedules;

public class DoctorDTO {

	private int id;

	private int clinicsId;
    private int specializationsId;
    
	private List<Schedules>schedules = new ArrayList<>();

    private int userId;
    
    private int status;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Schedules> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<Schedules> schedules) {
		this.schedules = schedules;
	}

	public int getClinicsId() {
		return clinicsId;
	}

	public void setClinicsId(int clinicsId) {
		this.clinicsId = clinicsId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getSpecializationsId() {
		return specializationsId;
	}

	public void setSpecializationsId(int specializationsId) {
		this.specializationsId = specializationsId;
	}

	public DoctorDTO(Doctor doctor) {

		this.id = doctor.getId();
		this.clinicsId = doctor.getClinics().getId();
		this.specializationsId = doctor.getSpecializations().getId();
		this.schedules = doctor.getSchedules();
		this.userId = doctor.getUser().getId();
		this.status = doctor.getStatus();
	}
	public DoctorDTO() {

	}
	
	


}
