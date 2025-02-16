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
            
            // List<Contact> contacts = new ArrayList<>(Arrays.asList(
            //     new Contact("Alejandro", "Cisneros", "ale@gmail.com", "2222222222", "Cerdena 124", "http://imageurl.com"),
            //     new Contact("Ana Paty", "Cisneros", "ana@gmail.com", "2222222222", "Cerdena 124", "http://imageurl.com")
            // ));

            Contact contact1 = new Contact("Alejandro", "Cisneros", "ale@gmail.com", "2222222222", "Cerdena 124", "http://imageurl.com");
            Contact contact2 = new Contact("Ana Paty", "Cisneros", "ana@gmail.com", "2222222222", "Cerdena 124", "http://imageurl.com");

            contactRepository.saveAll(Arrays.asList(contact1, contact2));
        };
        

    }
}
