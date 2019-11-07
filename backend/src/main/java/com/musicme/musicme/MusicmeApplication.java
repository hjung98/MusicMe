package com.musicme.musicme;

import com.musicme.musicme.config.AppProperties;
import com.musicme.musicme.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class MusicmeApplication{

	public static void main(String[] args) {
		// establish ssh connection
		SpringApplication.run(MusicmeApplication.class, args);
	}
}

