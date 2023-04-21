package com.example.control_hospital;

import com.example.control_hospital.entities.*;
import com.example.control_hospital.repositories.ConsultationRepository;
import com.example.control_hospital.repositories.MedecinRepository;
import com.example.control_hospital.repositories.PatientRepository;
import com.example.control_hospital.repositories.RendezVousRepository;
import com.example.control_hospital.service.HospitalServiceImpl;
import com.example.control_hospital.service.IHospitalService;
import com.example.control_hospital.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class ControlHospitalApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControlHospitalApplication.class, args);
	}

	/*@Bean
	CommandLineRunner start(HospitalServiceImpl hospitalService, MedecinRepository medecinRepository,
							RendezVousRepository rendezVousRepository, PatientRepository patientRepository,
							UserService userService){
		return args -> {
			Stream.of("Mohamed", "HASSAN", "Najat", "HASSAN2", "Najat2", "HASSAN3", "Najat3",
							"Mohamed", "HASSAN", "Najat", "HASSAN2", "Najat2", "HASSAN3", "Najat3",
							"Mohamed", "HASSAN", "Najat", "HASSAN2", "Najat2", "HASSAN3", "Najat3",
							"Mohamed", "HASSAN", "Najat", "HASSAN2", "Najat2", "HASSAN3", "Najat3",
							"Mohamed", "HASSAN", "Najat", "HASSAN2", "Najat2", "HASSAN3", "Najat3",
							"Mohamed", "HASSAN", "Najat", "HASSAN2", "Najat2", "HASSAN3", "Najat3",
							"Mohamed", "HASSAN", "Najat", "HASSAN2", "Najat2", "HASSAN3", "Najat3",
							"Mohamed", "HASSAN", "Najat", "HASSAN2", "Najat2", "HASSAN3", "Najat3",
							"Mohamed", "HASSAN", "Najat", "HASSAN2", "Najat2", "HASSAN3", "Najat3",
							"Mohamed", "HASSAN", "Najat", "HASSAN2", "Najat2", "HASSAN3", "Najat3",
							"Mohamed", "HASSAN", "Najat", "HASSAN2", "Najat2", "HASSAN3", "Najat3",
							"Mohamed", "HASSAN", "Najat", "HASSAN2", "Najat2", "HASSAN3", "Najat3",
							"Mohamed", "HASSAN", "Najat", "HASSAN2", "Najat2", "HASSAN3", "Najat3",
							"Mohamed", "HASSAN", "Najat", "HASSAN2", "Najat2", "HASSAN3", "Najat3",
							"Mohamed", "HASSAN", "Najat", "HASSAN2", "Najat2", "HASSAN3", "Najat3",
							"Mohamed", "HASSAN", "Najat", "HASSAN2", "Najat2", "HASSAN3", "Najat3")
					.forEach(name ->{
						Patient patient= new Patient();
						patient.setNom(name);
						patient.setDateNaissance(new Date());
						patient.setAge(23);
						patient.setScore(50);
						patient.setGenre(Math.random()>0.5?Genre.FEMME:Genre.HOMME);
						patient.setMalade(false);
						hospitalService.savePatient(patient);
					});
			Stream.of("Aymane", "Hanane", "Yasmine", "Hanane2", "Yasmine2")
					.forEach(name ->{
						Medecin medecin= new Medecin();
						medecin.setNom(name);
						medecin.setEmail(name+"@gmail.com");
						medecin.setSpecialite(Math.random()>0.5?"Cardio":"Dentiste");
						hospitalService.saveMedecin(medecin);
					});

			Stream.of(StatusRDV.PENDING, StatusRDV.DONE, StatusRDV.CANCELED)
					.forEach(status ->{
						Medecin medecin=medecinRepository.findByNom("Yasmine");
						Patient patient=patientRepository.findByNom("Mohamed");
						RendezVous rendezVous= new RendezVous();
						rendezVous.setDate(new Date());
						rendezVous.setStatus(status);
						rendezVous.setMedecin(medecin);
						rendezVous.setPatient(patient);
						RendezVous rendezVous1= hospitalService.saveRendezVous(rendezVous);
						System.out.println(rendezVous1.getId());
					});


			RendezVous rendezVous1= rendezVousRepository.findAll().get(0);
			Consultation consultation= new Consultation();
			consultation.setDateConultation(new Date());
			consultation.setRendezVous(rendezVous1);
			consultation.setRapport("Rapport de consultation-----");
			hospitalService.saveConsultation(consultation);


			Stream.of("USER1", "USER2", "USER3", "USER4", "USER5").forEach(u->{
				User user= new User();
				user.setUserName((u));
				user.setUserPassword("12342");
				userService.addNewUser(user);
			});

			Stream.of("STUDENT", "USER", "ADMIN").forEach(r->{
				Role role1= new Role();
				role1.setRoleName(r);
				userService.addNewRole(role1);
			});

			userService.addRoleToUser("USER1", "STUDENT");
			userService.addRoleToUser("USER1", "USER");
			userService.addRoleToUser("USER1", "ADMIN");
			userService.addRoleToUser("USER2", "USER");
			userService.addRoleToUser("USER2", "STUDENT");
			userService.addRoleToUser("USER4", "ADMIN");
			userService.addRoleToUser("USER3", "ADMIN");


		};
	}*/

}
