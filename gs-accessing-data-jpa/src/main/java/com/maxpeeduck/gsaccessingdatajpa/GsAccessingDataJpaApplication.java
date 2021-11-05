package com.maxpeeduck.gsaccessingdatajpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GsAccessingDataJpaApplication {

    private static final Logger log = LoggerFactory.getLogger(GsAccessingDataJpaApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(GsAccessingDataJpaApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(CustomerRepository repository) {
        return (args) -> {
           // save a few customer
           repository.save(new Customer("Jack", "Bauer"));
            repository.save(new Customer("Chloe", "O'Brian"));
            repository.save(new Customer("Kim", "Bauer"));
            repository.save(new Customer("David", "Palmer"));
            repository.save(new Customer("Michelle", "Dessler"));

            // fetch all customer
            log.info("Customers founds with findAll():");
            log.info("--------------------------------");
            for (Customer customer : repository.findAll()) {
                log.info(customer.toString());
            }
            log.info("================================");

            // fetch an individual cutomer by ID
            Customer customer = repository.findById(1L);
            log.info("Customers founds with findById():");
            log.info("--------------------------------");
            log.info(customer.toString());
            log.info("================================");

            // fetch customer by last name
            log.info("Customers founds with findByLastName('Bauer'):");
            log.info("--------------------------------");
            repository.findByLastName("Bauer").forEach(bauer -> {
                log.info(bauer.toString());
            });

            // Replace lamda such as next line
            // repository.findByLastName("Bauer").forEach(bauer -> log.info(bauer.toString()));

//            for (Customer bauer : repository.findByLastName("Bauer")) {
//                log.info(bauer.toString());
//            }
            log.info("================================");
        };
    }
}
