package com.example.demo.dto;

import com.example.demo.model.Schedules;

public class SchedulesDTO {

	private int id;
	
	private int doctorId;
	
	private int patientsId;
    
	private String time;
	
	private int status;

    private String description;
    
	public int getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
	public int getPatientsId() {
		return patientsId;
	}
	public void setPatientsId(int patientsId) {
		this.patientsId = patientsId;
	}

	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}

	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public SchedulesDTO() {

	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public SchedulesDTO(Schedules schedules) {

		this.id = schedules.getId();
		this.doctorId = schedules.getDoctor().getId();
		this.patientsId = schedules.getPatients().getId();
		this.time = schedules.getTime();
		this.status = schedules.getStatus();
		this.description = schedules.getDescription();
	}
	public SchedulesDTO(int id, int doctorId, int patientsId, String time, int status, String description) {

	}


}
