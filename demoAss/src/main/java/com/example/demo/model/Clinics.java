package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "clinic")
public class Clinics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private int id;
    
    @Column(name = "name")
	private String name;
    
    @Column(name = "address")
	private String address;
    
    @Column(name = "phone")
	private String phone;
    
    @Column(name = "introductionHTML")
	private String introductionHTML;
    
    @Column(name = "introductionMarkdown")
	private String introductionMarkdown;
    
    @Column(name = "description")
	private String description;
    
    @Column(name = "image")
	private String image;

	@OneToMany( fetch = FetchType.EAGER, mappedBy = "clinics")
	private List<Doctor>doctorUsers = new ArrayList<>();
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getIntroductionHTML() {
		return introductionHTML;
	}
	public void setIntroductionHTML(String introductionHTML) {
		this.introductionHTML = introductionHTML;
	}
	public String getIntroductionMarkdown() {
		return introductionMarkdown;
	}
	public void setIntroductionMarkdown(String introductionMarkdown) {
		this.introductionMarkdown = introductionMarkdown;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public List<Doctor> getDoctorUsers() {
		return doctorUsers;
	}
	public void setDoctorUsers(List<Doctor> doctorUsers) {
		this.doctorUsers = doctorUsers;
	}
	
	public int getSumDoctor() {
		return getDoctorUsers().size();
	}
	
}
