package com.example.control_hospital;

import com.example.control_hospital.entities.*;
import com.example.control_hospital.repositories.ConsultationRepository;
import com.example.control_hospital.repositories.MedecinRepository;
import com.example.control_hospital.repositories.PatientRepository;
import com.example.control_hospital.repositories.RendezVousRepository;
import com.example.control_hospital.security.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class ControlHospitalApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControlHospitalApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	//@Bean
	CommandLineRunner commandLineRunner(JdbcUserDetailsManager jdbcUserDetailsManager){
		PasswordEncoder passwordEncoder= passwordEncoder();
		return args -> {

			UserDetails u1 = jdbcUserDetailsManager.loadUserByUsername("user1");
			if (u1 == null)
				jdbcUserDetailsManager.createUser(
						User.withUsername("user1").password(passwordEncoder.encode("1234")).roles("USER").build()
				);

			UserDetails u2 = jdbcUserDetailsManager.loadUserByUsername("user2");
			if (u2 == null)
				jdbcUserDetailsManager.createUser(
						User.withUsername("user2").password(passwordEncoder.encode("1234")).roles("USER").build()
				);

			UserDetails u3 = jdbcUserDetailsManager.loadUserByUsername("admin");
			if (u3 == null)
				jdbcUserDetailsManager.createUser(
						User.withUsername("admin").password(passwordEncoder.encode("1234")).roles("USER", "ADMIN").build()
				);
		};
	}

	//@Bean
	CommandLineRunner commandLineRunnerUserDetails(AccountService accountService){
		return args -> {
			//accountService.addNewRole("USER");
			//accountService.addNewRole("ADMIN");

			accountService.addNewUser("user4", "1234","user4@gmail.com", "1234");
			accountService.addNewUser("user3", "1234","user3@gmail.com", "1234");
			accountService.addNewUser("admin3", "1234","admin3@gmail.com", "1234");
			accountService.addNewUser("admin2", "1234","admin3@gmail.com", "1234");

			accountService.addRoleToUser("user4", "USER");
			accountService.addRoleToUser("user3", "USER");
			accountService.addRoleToUser("admin2", "USER");
			accountService.addRoleToUser("admin3", "ADMIN");
		};
	}

	//@Bean
	CommandLineRunner commandLineRunner(PatientRepository patientRepository, MedecinRepository medecinRepository){
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
						patientRepository.save(patient);
					});
			Stream.of("Aymane", "Hanane", "Yasmine", "Hanane2", "Yasmine2")
					.forEach(name ->{
						Medecin medecin= new Medecin();
						medecin.setNom(name);
						medecin.setEmail(name+"@gmail.com");
						medecin.setSpecialite(Math.random()>0.5?"Cardio":"Dentiste");
						medecinRepository.save(medecin);
					});
		};

	}


	/*@Bean
	CommandLineRunner start(HospitalServiceImpl hospitalService, MedecinRepository medecinRepository,
							RendezVousRepository rendezVousRepository, PatientRepository patientRepository,
							UserService userService){
		return args -> {
			try {
				Stream.of(StatusRDV.PENDING, StatusRDV.DONE, StatusRDV.CANCELED)
						.forEach(status ->{
							Medecin medecin=medecinRepository.findByNom("lucils");
							Patient patient=patientRepository.findByNom("noctis");
							RendezVous rendezVous= new RendezVous();
							rendezVous.setDate(new Date());
							rendezVous.setStatus(status);
							rendezVous.setMedecin(medecin);
							rendezVous.setPatient(patient);
							RendezVous rendezVous1= hospitalService.saveRendezVous(rendezVous);
							System.out.println(rendezVous1.getId());
						});
			} catch (IncorrectResultSizeDataAccessException e) {
				System.err.println("Erreur : la requête n'a pas renvoyé un résultat unique.");
				e.printStackTrace();
				// Ajoutez ici le comportement de secours à exécuter en cas d'erreur.
			}

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
