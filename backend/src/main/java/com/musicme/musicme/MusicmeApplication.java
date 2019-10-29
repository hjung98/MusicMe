package com.musicme.musicme;

import com.musicme.musicme.mappers.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
<<<<<<< HEAD
		logger.debug("running app");
		System.out.println(this.userMapper.findAll());
=======
		System.out.println(this.userRepository.findAll());
>>>>>>> c4834df86e6b154ffc33ba190d49ca7edf6610f9
	}
}
