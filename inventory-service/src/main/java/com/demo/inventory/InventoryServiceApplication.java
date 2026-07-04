package com.demo.inventory;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.demo.inventory.entity.InventoryItem;
import com.demo.inventory.repository.InventoryRepository;


@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    // Add some inventory data on startup - then comment this bean
//    @Bean
//    CommandLineRunner sampleData(InventoryRepository repo) {
//        return args -> {
//            repo.save(new InventoryItem(null, "SKU-001", "Laptop", 50,50000));
//            repo.save(new InventoryItem(null, "SKU-002", "Mouse",100, 500));
//            repo.save(new InventoryItem(null, "SKU-003", "Keyboard",60, 450));
//            repo.save(new InventoryItem(null, "SKU-004", "Monitor",70  ,8000));
//            repo.save(new InventoryItem(null, "SKU-005", "Headphones",0, 600));   // out-of-stock
//            System.out.println("Inventory seeded with 5 products");
//        };
//    }
    
    @Bean // method level annotation - to declare a method returning java object
	ModelMapper modelMapper() {
		ModelMapper mapper = new ModelMapper();
		// configure mapper - to transfer the matching props (name + data type)
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT)
				// configure mapper - not to transfer nulls from src -> dest
				.setPropertyCondition(Conditions.isNotNull());
		return mapper;// Method rets configured ModelMapper bean to SC
	}
}
