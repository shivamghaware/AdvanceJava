package com.hms;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Day17HealthCarePracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(Day17HealthCarePracticeApplication.class, args);
	}

	@Bean
	ModelMapper modelMapper(){
		ModelMapper mapper=new ModelMapper();
		mapper.getConfiguration()
		.setMatchingStrategy(MatchingStrategies.STRICT)//configure mapper - not to transfer nulls from src -> dest
		.setPropertyCondition(Conditions.isNotNull());
		return mapper;//Method rets configured ModelMapper bean to SC
	}
}
