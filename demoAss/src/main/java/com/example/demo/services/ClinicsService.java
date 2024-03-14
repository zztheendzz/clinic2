package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Clinics;
import com.example.demo.repository.ClinicsRepository;

@Service
public class ClinicsService {
	@Autowired
	private ClinicsRepository clinicsRepository;

	public List<Clinics> clinics() {
		List<Clinics> clinics = clinicsRepository.findAll();
		return clinics;
	}

	public Clinics findByIdClinics(int id) {
		Clinics clinics = clinicsRepository.findById(id).get();
		return clinics;
	}

	public List<Clinics> getListByAddress(String keyWord) {
		List<Clinics> list = clinicsRepository.findByAddressLike(keyWord).orElseGet(null);
		return list;
	}
	public Clinics outStand() {
		List<Clinics> clinics = clinicsRepository.findAll(); //10 
		Clinics c = new Clinics();
		int sumDoctor = 0;
		for (Clinics clinics2 : clinics) {
			int temp = clinics2.getSumDoctor();
			if(temp>sumDoctor) {
				c = clinics2;
				sumDoctor = temp;
			}
		}
		return c;
	}
}
