package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.SchedulesDTO;
import com.example.demo.model.Doctor;
import com.example.demo.model.Patients;
import com.example.demo.model.Schedules;
import com.example.demo.model.User;
import com.example.demo.repository.DoctorRepository;
import com.example.demo.repository.PatientsRepository;
import com.example.demo.repository.SchedulesRepository;

@Service
public class PatientsService {
	@Autowired
	PatientsRepository patientsRepository;

	@Autowired
	DoctorRepository doctorRepository;

	@Autowired
	SchedulesService schedulesService;

	@Autowired
	DoctorService doctorService;

	@Autowired
	SchedulesRepository schedulesRepository;

//	danh sach benh nhan theo doctorId
	public List<Patients> patientsbyDoctor(int doctorId) {
		List<Patients> list = new ArrayList<Patients>();
		List<Schedules> lSchedules = schedulesService.listByDoctorId(doctorId);
		if (lSchedules.size() > 0) {
			for (Schedules schedules : lSchedules) {
				list.add(schedules.getPatients());
			}
		}
		return list;
	}

	public Patients getPatientsById(int id) {
		try {
			Patients patients = patientsRepository.findById(id).get();
			System.out.println(patients.toString());
			return patients;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}

	}

	public Patients getPatientsByUser(User user) {
		Patients p = new Patients();
		List<Patients> list = patientsRepository.findAll();
		for (Patients patients2 : list) {
			if (patients2.getUser().getId() == user.getId()) {
				p = patients2;
				break;
			}
		}
		return p;
	}

//	add schedule
	public void addSchedules(SchedulesDTO schedulesDTO) {
//		Schedules schedules = new Schedules(schedulesDTO);
//
//		Patients patients = getPatientsById(schedulesDTO.getPatientsId());
//		schedules.setPatients(patients);
////		schedulesService.save(schedules);
//		schedulesRepository.saveAndFlush(schedules);
//		
//		Doctor doctor = doctorService.findById(schedulesDTO.getDoctorId());
//		doctor.getSchedules().add(schedules);
//		doctorService.update(doctor);

	}

	public void addSchedules1(SchedulesDTO schedulesDTO) {
//		Schedules schedules = new Schedules(schedulesDTO);
//		schedulesRepository.save(schedules);
//		Patients patients = getPatientsById(schedulesDTO.getPatientsId());
//		patients.getSchedules().add(schedules);
//		Doctor doctor = doctorService.findById(schedulesDTO.getDoctorId());
//		doctor.getSchedules().add(schedules);
//		
//		doctorRepository.saveAndFlush(doctor);
//		patientsRepository.saveAndFlush(patients);

	}

	public List<Schedules> getListScheByPatientsId(int patientsId) {

		List<Schedules> list = getPatientsById(patientsId).getSchedules();
		return list;
	}

	public List<SchedulesDTO> getListScheByPatientsIdDTO(int patientsId) {

		List<SchedulesDTO> listDTO = new ArrayList<>();
		List<Schedules> list = getPatientsById(patientsId).getSchedules();
		System.out.println(list.size() + "day la");
		for (Schedules schedules : list) {
			SchedulesDTO schedulesDTO = new SchedulesDTO(schedules);
			listDTO.add(schedulesDTO);
			System.out.println(listDTO.get(0).getDescription() + "day la ");
		}
		return listDTO;
	}
	public Schedules createSchedule(SchedulesDTO schedulesDTO) {
		Schedules schedules = new Schedules(schedulesDTO);
		schedules.setDoctor(doctorService.findById(schedulesDTO.getDoctorId()));
		schedules.setPatients(getPatientsById(schedulesDTO.getPatientsId()));
		
		return schedulesRepository.saveAndFlush(schedules);
		
	}

}
