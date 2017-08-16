package ru.megalabs.user_service;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Created by fruitjazzy on 16.08.17.
 */
@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		System.getProperties().setProperty("spring.config.location", "classpath:conf.yml");

		new SpringApplicationBuilder()
				.sources(Application.class)
				.bannerMode(Banner.Mode.OFF)
				.run();
	}
}
