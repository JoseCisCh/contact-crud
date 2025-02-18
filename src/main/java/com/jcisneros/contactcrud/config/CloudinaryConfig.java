package com.jcisneros.contactcrud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.cloudinary.Cloudinary;

@Configuration
public class CloudinaryConfig {



    @Bean
    Cloudinary cloudinary(Environment environment) {
        String cloudinaryUrl = environment.getProperty("CLOUDINARY_URL");
        return new Cloudinary(cloudinaryUrl);
    }
}
