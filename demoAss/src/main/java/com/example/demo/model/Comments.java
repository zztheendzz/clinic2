package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "comment")
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private int id;
	private int doctorId;
	private String timeBooking;
	private String dateBooking;
	private String name;
	private String phone;
	private String content;
	private int status;
	private String createAt;
	private String updateAt;
	private String deletedAt;
}
