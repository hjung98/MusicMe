package com.musicme.musicme;

import com.musicme.musicme.entities.User;
import com.musicme.musicme.mappers.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
public class MusicmeApplication implements CommandLineRunner{

	private Logger logger;

	private final UserMapper userMapper;

	public MusicmeApplication(UserMapper userMapper) {
		this.userMapper = userMapper;
		this.logger = LoggerFactory.getLogger(this.getClass());
	}

	public static void main(String[] args) {
		SpringApplication.run(MusicmeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.debug("running app");
		System.out.println(this.userMapper.findAll());
	}
}
