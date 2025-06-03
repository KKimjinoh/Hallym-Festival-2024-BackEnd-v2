package com.kkimjinoh.movieadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
		"com.kkimjinoh.movieadmin.domain",
		"com.kkimjinoh.global",
})
public class MovieAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieAdminApplication.class, args);
	}

}
