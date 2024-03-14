package com.example.demo.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Doctor;
import com.example.demo.model.Specializations;
import com.example.demo.repository.SpecializationsRepository;

@Service
public class SpecializationsService {
	@Autowired
	private SpecializationsRepository specializationsRepository;

	public List<Specializations> findAlList() {
		List<Specializations> specializations = specializationsRepository.findAll();
		return specializations;
	}

	public Specializations findById(int id) {
		Specializations specializations = specializationsRepository.findById(id).get();
		return specializations;
	}

//	tim kiem specialization theo so benh nhan nhieu nhat
	public Specializations OutstandingSpecial() {
		Specializations specializations = new Specializations();
		List<Specializations> list = findAlList();
		Map<Specializations, Integer> map = new HashMap<Specializations, Integer>();

		for (Specializations specializations2 : list) {
			int count = 0;
			List<Doctor> listDoctors = specializations2.getDoctors();
			for (Doctor doctor : listDoctors) {
				count = count + doctor.getSchedules().size();
			}
			map.put(specializations2, count);
		}

		Map<Specializations, Integer> sortedMapByValue = map.entrySet().stream()
				.sorted((o1, o2) -> o2.getValue().compareTo(o1.getValue())).collect(Collectors.toMap(Map.Entry::getKey,
						Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));
		Set<Specializations> set = sortedMapByValue.keySet();
		if (!set.isEmpty()) {
			for (Specializations specializations2 : set) {
				specializations = specializations2;
				break;
			}
		} else {
			return null;
		}

		return specializations;
	}
//	List<Specializations>getListFromAddress(String keyWrod){
//		List<Specializations>list = specializationsRepository.findByAddress(keyWrod).get();
//		return list;
//	}

}
