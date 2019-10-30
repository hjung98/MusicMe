package com.musicme.musicme;

import com.musicme.musicme.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MusicmeApplication implements CommandLineRunner {

	@Autowired
	UserServiceImpl userService;

	public MusicmeApplication(UserServiceImpl userService) {
		this.userService = userService;
	}

	public static void main(String[] args) {
		SpringApplication.run(MusicmeApplication.class, args);
	}

	public void run(String... args) throws Exception {
	}
}
