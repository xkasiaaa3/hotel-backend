package com.tijo.kw.hotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import java.time.LocalDate;

@SpringBootApplication
public class HotelApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelApplication.class, args);
	}

}
