package com.kata.bankaccount.api;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = { "com.kata.bankaccount", "com.kata.bankaccount.services" })
@EntityScan(basePackages = { "com.kata.bankaccount.entite" })
@EnableJpaRepositories(basePackages = { "com.kata.bankaccount.repository" })
public class BankAccountExposition extends SpringBootServletInitializer {
	
    public static void main(final String... args) {
        final SpringApplicationBuilder application = new SpringApplicationBuilder();
        application.sources(BankAccountExposition.class).run(args);
    }

}
