package com.example.Teaching_based_system;

import com.example.Teaching_based_system.Entity.User;
import com.example.Teaching_based_system.Repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TeachingBasedSystemApplication implements CommandLineRunner {
	@Autowired
	private UserRepo userRepo;

	public static void main(String[] args) {
		SpringApplication.run(TeachingBasedSystemApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		User user = new User(0,"ADMIN","$2a$10$ZFn5r4kCBcTaAlXuJgTC2.SgRfSqgAh35D6Rq9SX9Wqx1u3LTztiy","Admin","System","ADMIN","Admin@gmail.com","ADMIN","0763456987","200020002000","2022-02-02");
		if ((userRepo.findByName(user.getName()))==null){
			userRepo.save(user);
		}
		else if(!userRepo.existsById((userRepo.findByName(user.getName())).getId()) ){
			userRepo.save(user);
		}
	}
}
