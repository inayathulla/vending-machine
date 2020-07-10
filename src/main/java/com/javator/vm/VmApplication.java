package com.javator.vm;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VmApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(VmApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hello! please enter coin(s)");
		
	}
}
