package com.jcisneros.contactcrud.contact;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContactConfig {

    @Bean
    @Transactional
    CommandLineRunner commandLineRunner(ContactRepository contactRepository) {
        return args -> {
            
            

            Contact contact1 = new Contact("Fernanda", "Nunez", "fer@gmail.com", "2222222222", "Dir 1", null);
            Contact contact2 = new Contact("Diego", "Maradona", "diego@gmail.com", "2222222222", "Dir 2", null);

            contactRepository.saveAll(Arrays.asList(contact1, contact2));
        };
        

    }
}
