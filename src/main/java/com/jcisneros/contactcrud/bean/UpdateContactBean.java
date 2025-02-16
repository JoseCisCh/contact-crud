package com.jcisneros.contactcrud.bean;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.annotation.ManagedProperty;
import javax.faces.component.FacesComponent;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import com.jcisneros.contactcrud.contact.Contact;
import com.jcisneros.contactcrud.contact.ContactRepository;
import com.jcisneros.contactcrud.services.PhotoService;

@Component("updateContactBean")
@ViewScoped
public class UpdateContactBean implements Serializable {


    @Autowired
    ContactRepository contactRepository;

    @Autowired
    PhotoService photoService;

    Contact contact;
    private UploadedFile photoFile;

    
    @PostConstruct
    public void init(){
        
        String contactId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("contactId");
        
        if(contactId == null) {
            this.contact = new Contact();
            return;
        }
        
        Optional<Contact> optContact = contactRepository.findById(Long.parseLong(contactId));
        
        if(optContact.isPresent()) {
            this.contact = optContact.get();
        } else {
            this.contact = new Contact();
        }
        
    }
    
    public String save() {

        // if(this.photoFile != null) {
        //     try {
        //         InputStream photoStream = new FileInputStream(this.photoFile.getFileName());
        //         Map<String,String> result;
        //         result = photoService.uploadImage(photoStream);

        //         if(result.get("url") != null) {
        //             this.contact.setImageUrl(result.get("url"));
        //         }
        //     } catch(IOException err) {
        //         System.out.println("Error getting photo input stream: " + err);
        //     }
            
        // }

        contactRepository.save(this.contact);
        return "/views/contact-list.xhtml?faces-redirect=true";
    }

    public void handleFileUpload(FileUploadEvent event) {
        this.photoFile = event.getFile();
        if(this.photoFile != null) {
            
                byte[] photoStream = this.photoFile.getContent();
                Map<String,String> result;
                result = photoService.uploadImage(photoStream);

                if(result.get("url") != null) {
                    this.contact.setImageUrl(result.get("url"));
                }
            
            
        }
    }
    
    public Contact getContact() {
        return this.contact;
    }
    
    public void setContact(Contact contact) {
        this.contact = contact;
    }
    
    public UploadedFile getPhotoFile() {
        return photoFile;
    }
    
    public void setPhotoFile(UploadedFile photoFile) {
        this.photoFile = photoFile;
    }
}
