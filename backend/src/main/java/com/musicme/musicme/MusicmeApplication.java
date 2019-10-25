package com.musicme.musicme;

import com.musicme.musicme.mappers.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MusicmeApplication implements CommandLineRunner{


	private final UserRepository userRepository;

	public MusicmeApplication(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(MusicmeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(this.userRepository.findAll());
	}
}
