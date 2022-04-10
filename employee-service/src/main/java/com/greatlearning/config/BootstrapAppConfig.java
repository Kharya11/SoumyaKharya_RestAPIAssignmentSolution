package com.greatlearning.config;

import java.time.ZoneId;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

import com.github.javafaker.Faker;
import com.greatlearning.model.Employee;
import com.greatlearning.model.Role;
import com.greatlearning.model.User;
import com.greatlearning.repository.EmployeeRepository;
import com.greatlearning.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class BootstrapAppConfig implements ApplicationListener<ApplicationReadyEvent> {
	private final EmployeeRepository employeeRepository;

	private final UserRepository userRepository;;

	private final Faker faker = new Faker();

	public BootstrapAppConfig(EmployeeRepository employeeRepository, UserRepository userRepository) {
		this.employeeRepository = employeeRepository;
		this.userRepository = userRepository;
	}

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) 
	{
		for (int i = 0; i < 50; i++) 
		{
			Employee employee = Employee.builder().email(faker.internet().emailAddress())
					.fName(faker.name().firstName()).lName(faker.name().lastName()).build();
		
			this.employeeRepository.save(employee);
		}
		
	}

	User soumya = User.builder().name("soumya").password("welcome").build();
	User vishal = User.builder().name("vishal").password("welcome").build();
	
	Role user = Role.builder().roleName("USER").build();
	Role admin = Role.builder().roleName("ADMIN").build();
	
	{	
	  soumya.addRole(user); 
	  vishal.addRole(user); 
	  soumya.addRole(admin);
	  
	}

}


