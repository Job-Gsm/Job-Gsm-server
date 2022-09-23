package com.project.JobGsm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
public class JobGsmApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobGsmApplication.class, args);
	}

}
