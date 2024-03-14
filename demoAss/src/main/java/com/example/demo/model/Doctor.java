
package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.dto.UserDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "doctor")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private int id;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "clinicId")
    @JsonIgnore
	private Clinics clinics;
    
//	@OneToMany( fetch = FetchType.EAGER, mappedBy = "doctor")
	@OneToMany( fetch = FetchType.EAGER)
    @JsonIgnore
	private List<Schedules>schedules = new ArrayList<>();
	
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;
    

    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "specialization_id", referencedColumnName = "id")
    @JsonIgnore
    private Specializations specializations;
    
    
    
    @Column(name = "status")
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

	public Clinics getClinics() {
		return clinics;
	}

	public void setClinics(Clinics clinics) {
		this.clinics = clinics;
	}

	public List<Schedules> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<Schedules> schedules) {
		this.schedules = schedules;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Specializations getSpecializations() {
		return specializations;
	}

	public void setSpecializations(Specializations specializations) {
		this.specializations = specializations;
	}

	@Override
	public String toString() {
		return "Doctor [id=" + id + ", user=" + user + ", specializations=" + specializations + "]";
	}

    
 
	
}
