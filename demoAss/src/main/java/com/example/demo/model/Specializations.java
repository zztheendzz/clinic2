package com.example.demo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "specialization")
public class Specializations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private int id;
    @Column(name = "name")
	private String name;
    
    @Column(name = "description")
	private String description;
    
    @Column(name = "image")
	private String image;
    
    @OneToMany(mappedBy = "specializations",fetch = FetchType.EAGER)
    @JsonIgnore
    //eclipse ko tựdđộng save file àk?k, moi lan save no tu chay lai 
    //àk có gắn spring devtool
    private List<Doctor> doctors ;

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

	public List<Doctor> getDoctors() {
		return doctors;
	}

	public void setDoctors(List<Doctor> doctors) {
		this.doctors = doctors;
	}

	@Override
	public String toString() {
		return "Specializations [id=" + id + ", name=" + name + ", description=" + description + ", image=" + image
				+ "]";
	}
    
}
