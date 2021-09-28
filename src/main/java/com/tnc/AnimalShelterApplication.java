package com.tnc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.File;

import static com.tnc.service.constant.FileConstant.USER_FOLDER;

@SpringBootApplication
public class AnimalShelterApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnimalShelterApplication.class, args);
		new File(USER_FOLDER).mkdirs(); //automatic create folders for user home
	}

}
