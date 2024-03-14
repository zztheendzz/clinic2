package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "extrainfos")
public class Extrainfos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private int id;
	private int patientId;
	private String historyBreath;
	private int placeId;
	private String oldForms;
	private String moreInfo;
	private String CreateAt;
	private String UpdateCreateAt;
	private String DeletedAt;
}
