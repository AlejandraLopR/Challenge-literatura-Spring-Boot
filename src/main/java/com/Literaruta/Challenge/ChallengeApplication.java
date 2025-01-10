package com.Literaruta.Challenge;

import com.Literaruta.Challenge.principal.Principal;
import com.Literaruta.Challenge.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChallengeApplication implements CommandLineRunner	 {
	@Autowired
	private LibroRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(ChallengeApplication.class, args);
	}

	@Override
	public void run(String... args) throws  Exception {
		Principal principal = new Principal(repository);
		principal.muestraElMenu();
	}
}
