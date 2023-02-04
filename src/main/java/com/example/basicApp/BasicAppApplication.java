package com.example.basicApp;

import com.example.basicApp.security.AppProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BasicAppApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(BasicAppApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return super.configure(builder);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
    public SpringApplicationContext springApplicationContext() {
	    return new SpringApplicationContext();
    }
    @Bean
    public AppProperties getAppProprieties() {
	    return new AppProperties();
    }
}
