package com.jcisneros.contactcrud.services;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;

import io.github.cdimascio.dotenv.Dotenv;


@Service
public class PhotoService {
    private final Dotenv dotenv = Dotenv.load();
    
    private final Cloudinary cloudinary = new Cloudinary(dotenv.get("CLOUDINARY_URL"));
    
    public Map uploadImage(File file) {
        
        Map params1 = ObjectUtils.asMap(
            "use_filename", false,
            "unique_filename", false,
            "overwrite", true,
            "folder", "contact-crud",
            "transformation", new Transformation().width(500).height(500).crop("fill").gravity("face")
            );
            
        try {
            Map result = cloudinary.uploader().upload(file, params1);
            return result;
            
        } catch(IOException e) {
            System.out.println("Error when uploading image." + e) ;
            return ObjectUtils.emptyMap();
        }
    }
        
}
    