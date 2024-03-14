package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.jwt.JwtService;
import com.example.demo.dto.DoctorDTO;
import com.example.demo.dto.EmailDTO;
import com.example.demo.dto.SchedulesDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.model.Clinics;
import com.example.demo.model.Doctor;
import com.example.demo.model.Patients;
import com.example.demo.model.Schedules;
import com.example.demo.model.Specializations;
import com.example.demo.model.User;
import com.example.demo.services.ClinicsService;
import com.example.demo.services.DoctorService;
import com.example.demo.services.MailService;
import com.example.demo.services.PatientsService;
import com.example.demo.services.SchedulesService;
import com.example.demo.services.SpecializationsService;
import com.example.demo.services.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/")
public class Controller {
	@Autowired
	private JwtService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private MailService mailService;

	@Autowired
	private DoctorService doctorService;

	@Autowired
	private PatientsService patientsService;

	@Autowired
	private UserService userService;

	@Autowired
	private SpecializationsService specializationsService;

	@Autowired
	private SchedulesService schedulesService;

	@Autowired
	private ClinicsService clinicsService;

	private Authentication authentication;
	private SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();

//	dang nhap - lay ma token
	@PostMapping("/authenticate")
	public String authenticateAndGetToken(@RequestBody User user) {
		/* System.out.println(patientsService.patientsbyDoctor(0).size()); */
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
		if (authentication.isAuthenticated()) {
			return jwtService.generateToken1(user.getEmail());
		} else {
			throw new UsernameNotFoundException("invalid user request !");
		}
	}

//	 dang ki tai khoan
	@PostMapping("/public/regisacount")
	public ResponseEntity<User> regis(@RequestBody User user) {
		if (userService.AddUser(user)) {
			return ResponseEntity.status(200).body(user);
		}
		return ResponseEntity.status(404).body(null);
	}

// quen mat khau 
	@PostMapping("/public/forgotPassWord")
	public ResponseEntity<String> forgotPassWord(@RequestBody EmailDTO emailDTO) {
		Optional<User> user = userService.getUserByEmailOptional(emailDTO.getTo());

		System.out.println(user.get().getEmail() + "email");
		if (user.isEmpty()) {
			return ResponseEntity.status(406).body("email k ton tai");
		} else {
			String head = "dat lai mk";
			String content = "link dan: http://localhost:8080/public/confirmPass/?email=" + emailDTO.getTo() + "&pass="
					+ emailDTO.newPass;
			boolean isMultipart = true;
			boolean isHtml = false;
			String path = null;
			mailService.sendEmail(emailDTO.getTo(), head, content, isMultipart, isHtml, path);
			return ResponseEntity.status(200).body("vao email " + emailDTO.getTo() + " de xac thuc mk");
		}
	}

//	xac thuc mk qua email
	@GetMapping("/public/confirmPass/")
	public ResponseEntity<String> confirmPass(@RequestParam(name = "pass") String newPass,
			@RequestParam(name = "email") String email) {
		userService.setPass(newPass, email);
		return ResponseEntity.status(200).body("cap nhat thanh cong");
	}

//	hien thi chuyen khoa noi bat
	@GetMapping("/public/outStandingSpecializations")
	public ResponseEntity<Specializations> outStandingSpecializations() {
		Specializations specializations = specializationsService.OutstandingSpecial();
		return ResponseEntity.status(200).body(specializations);
	}

//	hien thi clinics noi bat
	@GetMapping("/public/outStandingClinics")
	public ResponseEntity<Clinics> outStandingClinics() {
		Clinics clinics = clinicsService.outStand();
		return ResponseEntity.status(200).body(clinics);
	}

// hien thi thong tin ca nhan
	@GetMapping("/user/personalInfor")
	public ResponseEntity<User> personalInfor(Authentication authentication) {
		String nameString = authentication.getName();
		authentication.getName();
		authentication.getDetails().toString();
		return ResponseEntity.status(200).body(userService.getUserByEmail(nameString));
	}

//	Tim kiem chung
	// tim kiem theo dia chi
	@GetMapping("/public/SearchAddress/{address}")
	public ResponseEntity<List<Clinics>> SearchAddress(@PathVariable("address") String keySearch) {
		List<Clinics> list = clinicsService.getListByAddress(keySearch);
		System.out.println(keySearch);
		return ResponseEntity.status(200).body(list);
	}

//	Tìm kiếm theo bác sĩ theo chuyên khoa 
	@PostMapping("/public/SearchSpecializations")
	public ResponseEntity<List<DoctorDTO>> SearchSpecializations(@RequestBody String keySearch) {
		List<DoctorDTO> listDTO = doctorService.findByspecializationsDTO(keySearch);
		if (!listDTO.isEmpty()) {
			return ResponseEntity.status(200).body(listDTO);
		}

		return ResponseEntity.status(200).body(null);
	}

//	Dat lich kham
	@PostMapping("/user/createSchedules")
	public ResponseEntity<SchedulesDTO> schedules(@RequestBody SchedulesDTO schedulesDTO) {
		Schedules schedules = patientsService.createSchedule(schedulesDTO);
		return ResponseEntity.status(200).body(schedulesDTO);
	}

	// danh sach benh nhan cua bac si
	@GetMapping("/doctor/listPatients/{doctorId}")
	public ResponseEntity<List<Patients>> listPatients(@PathVariable int doctorId) {
		try {
			List<Patients> list = schedulesService.findByDoctorId(doctorId);
			return ResponseEntity.status(200).body(list);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(403).body(null);
		}

	}

//	Nhận/hủy lịch khám của bệnh nhân
	@PostMapping("/doctor/confirmSchedules")
	public ResponseEntity<SchedulesDTO> confirmSchedules(@RequestBody SchedulesDTO schedulesDTO) {
		SchedulesDTO schedulesDTO2 = schedulesService.setStatusAndDesByIdDTO(schedulesDTO);
		return ResponseEntity.status(200).body(schedulesDTO2);
	}

//	 Khóa/hủy khóa tài khoản của bệnh nhân/bac si
	@PostMapping("/admin/statusUser/{userId}")
	public ResponseEntity<User> lockUser(@PathVariable int userId) {
		User user = userService.setStatus(userId);
		return ResponseEntity.status(200).body(user);
	}

//	Thêm tài khoản của bác sĩ
	@PostMapping("/admin/addDoctor")
	public ResponseEntity<UserDTO> addDoctor(@RequestBody UserDTO userDTO) {
		if (userService.AddUser(userDTO)) {
			return ResponseEntity.status(200).body(userDTO);
		} else {
			return ResponseEntity.status(406).body(userDTO);
		}
	}

//	Gửi thông tin về email cá nhân của bệnh nhân
	@PostMapping("/doctor/sendMail")
	public ResponseEntity<String> sendMail(@RequestBody EmailDTO emailDTO) {
		emailDTO.setMultipart(false);
		emailDTO.setHtml(false);
		boolean sendMailSuccess = mailService.sendEmail(emailDTO);
		if (sendMailSuccess) {
			return ResponseEntity.status(200).body("gui mail thanh cong");
		} else {
			return ResponseEntity.status(403).body("gui mail that bai");
		}
//		mailService.sendEmail(emailDTO.getTo(), emailDTO.getSubject(), emailDTO.getContent(), emailDTO.isMultipart(),
//				emailDTO.isHtml(), emailDTO.getPath());
	}

//  Admin Xem thông tin chi tiết lịch khám của từng bệnh nhân
	@GetMapping("/admin/schedulesPatients/{patientsId}")
	public ResponseEntity<List<SchedulesDTO>> schedulePatients(@PathVariable int patientsId) {
		List<SchedulesDTO> list = schedulesService.findSchedulesByPatientsIdDTO(patientsId);
		return ResponseEntity.status(200).body(list);

	}

//	Admin Xem thông tin chi tiết lịch khám của từng bác sĩ
	@GetMapping("/admin/schedulesDoctor/{doctorId}")
	public ResponseEntity<List<Schedules>> scheduleDoctor(@PathVariable int doctorId) {
		List<Schedules> list = schedulesService.findSchedulesByDoctorId(doctorId);
		return ResponseEntity.status(200).body(list);
	}

//	//////////////////////////////////////////////////////////////////////////////////////////////////
//	test send mail
	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String getMethodName() {
		mailService.sendEmail("dinhkhongcanchinh@gmail.com", "head", "content", true, false, "D:\\dow1\\ass3.txt");
		return "test";
	}

//	test send mail
	@GetMapping("/public/admin")
	public String getMethodName2() {
		mailService.sendEmail("dinhkhongcanchinh@gmail.com", "head", "content", true, false, "D:\\dow1\\ass3.txt");
		return "test";
	}

//	///////////////////////////////////////////////////////////////////////////////////
}
