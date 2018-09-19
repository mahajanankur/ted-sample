package com.sb.hackerearth.ted;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ankur.mahajan
 * @written 18-Sep-2018
 */
@SpringBootApplication
@EnableAutoConfiguration
public class BootStarter {
	public static void main(String[] args) {
		SpringApplication.run(BootStarter.class, args);
	}
}
