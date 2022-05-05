package com.ofrs;

import java.util.Properties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.filter.CommonsRequestLoggingFilter;


/*
 * @SpringToolSuit version 3.4.0
 * @Date 15/04/2022
 * Main class
 *  
 * */


/*
 * @SpringBootApplication annotation is used to mark a configuration class that declares one or more @Bean methods and also triggers auto-configuration and component scanning.
 * @EnableJpaRepositories scan the packages for configuration and repository class for Spring Data JPA.
 * @EntityScan Configures the base packages used by auto-configuration when scanning for entity classes.
 * @CrossOrigin enables cross-origin resource sharing only for this specific method.
 * @Bean to mark a method as one that creates a bean and Spring will then add it to the context for us.
 * 
 * */
@SpringBootApplication(scanBasePackages = "com.ofrs")
@EnableJpaRepositories(basePackages = "com.ofrs.repository")
@EntityScan(basePackages = "com.ofrs.model")
@CrossOrigin("*")
public class OfrsMajorApplication {

	public static void main(String[] args) {
		SpringApplication.run(OfrsMajorApplication.class, args);
	}

	@Bean
	public CommonsRequestLoggingFilter logFilter() {
		CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
		filter.setIncludeQueryString(true);
		filter.setIncludePayload(true);
		filter.setMaxPayloadLength(1000);
		filter.setIncludeHeaders(false);
		filter.setAfterMessagePrefix("REQUEST DATA : ");
		return filter;
	}
	
	@Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("webmail.evolvingsols.com");
        mailSender.setPort(587);
          
        mailSender.setUsername("Trng1");
        mailSender.setPassword("Cyb@ge@531");
          
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable.required", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.ssl.enable.trust", "*");
        return mailSender;
    }
}
