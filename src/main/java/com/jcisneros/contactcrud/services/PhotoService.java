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


    /**
     *  Cloudinary SDK instance to upload images
     */
    private final Cloudinary cloudinary;
    

    
    public PhotoService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    /**
     * Uploads image to Cloudify and returns Map with result of the 
     * upload operation.
     * 
     * @param file  File containing image to upload.
     * @return Map with the result returned from Cloudinary SDK.
     */
    public Map<String, Object> uploadImage(File file) {
        
        Map<String, Object> params1 = ObjectUtils.asMap(
            "use_filename", false,
            "unique_filename", false,
            "overwrite", true,
            "folder", "contact-crud",
            "transformation", new Transformation().width(500).height(500).crop("fill").gravity("face")
            );
            
        try {
            Map<String, Object> result = cloudinary.uploader().upload(file, params1);
            return result;
            
        } catch(IOException e) {
            System.out.println("Error when uploading image." + e) ;
            return ObjectUtils.emptyMap();
        }
    }
        
}
    