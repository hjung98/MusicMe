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

@SpringBootApplication(scanBasePackages = {"com.musicme.musicme.mappers"})
@EntityScan("com.musicme.musicme.mappers")
public class MusicmeApplication implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
	private final UserMapper userMapper;

	public MusicmeApplication(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	@Override
	public void run(String...args) throws  Exception {
		logger.info("Inserting -> {}", userMapper.insert(new User(10011L,"ram004", "Ramesh", "Fadatare", "ramesh@gmail.com")));
		logger.info("Inserting -> {}", userMapper.insert(new User(10012L, "john004", "John", "Cena", "john@gmail.com")));
		logger.info("Inserting -> {}", userMapper.insert(new User(10013L, "tony004", "tony", "stark", "stark@gmail.com")));

		logger.info("Employee id 10011 -> {}", userMapper.findById(10011L));

		logger.info("Update 10003 -> {}", userMapper.update(new User(10011L, "ram004","ram", "Stark", "ramesh123@gmail.com")));

		userMapper.deleteById(10013L);

		logger.info("All users -> {}", userMapper.findAll());
	}
	public static void main(String[] args) {
		SpringApplication.run(MusicmeApplication.class, args);
	}
}
