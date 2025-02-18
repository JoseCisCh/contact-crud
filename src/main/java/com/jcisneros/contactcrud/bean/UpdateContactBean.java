package com.jcisneros.contactcrud.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jcisneros.contactcrud.contact.Contact;
import com.jcisneros.contactcrud.contact.ContactRepository;
import com.jcisneros.contactcrud.services.PhotoService;

@Component("updateContactBean")
@ViewScoped
public class UpdateContactBean implements Serializable {


    /**
     *  ContactRepository injected to execute operations in 
     *  Database
     */
    @Autowired
    ContactRepository contactRepository;

    /**
     *  PhotoService injected to upload image.
     */
    @Autowired
    PhotoService photoService;

    /**
     *  Current contact to edit
     */
    Contact contact;

    private File photoFile;

    
    /**
     * Executed when creation of the bean. 
     * If contactId is provided searches for the contact
     * and creates sets contact.
     * If no contactId is provided create new contact.
     */
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
    

    /**
     * Saves the current contact in the database
     * If photo file was uploaded then PhotoService is
     * called to upload image.
     * 
     * @return url to return to contact list view.
     */
    public String save() {

        if(this.photoFile != null) {
            
            try {
                //InputStream photoStream = new FileInputStream(this.photoFile);
                Map<String,Object> result;
                
                result = photoService.uploadImage(photoFile);

                if(result.get("url") != null) {
                    this.contact.setImageUrl((String) result.get("url"));
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        contactRepository.save(this.contact);
        return "/views/contact-list.xhtml?faces-redirect=true";
    }

    /**
     * Creates a File in tomcate storage to avoid saving the iamge file in memory
     * after handleFileUpload.
     * 
     * @param event event containing file uploaded by user
     */
    public void handleFileUpload(FileUploadEvent event) {
        UploadedFile uploadedFile = event.getFile();

        try {
            String uploadsPath = System.getProperty("catalina.base") + "/webapps/uploads";
            File targetDir = new File(uploadsPath);
            if (!targetDir.exists()) {
                targetDir.mkdirs(); // Create directory if it doesn't exist
            }

            File file = new File(uploadsPath, uploadedFile.getFileName());

            try (InputStream input = uploadedFile.getInputStream();
             OutputStream output = new FileOutputStream(file)) {
                IOUtils.copy(input, output); // Apache Commons IO
            }
            
            this.photoFile = file;
        } catch(Exception e) {
            e.printStackTrace();
        }
        
    }
    

    /*
    * The following are only getters and setter methods
    */

    public Contact getContact() {
        return this.contact;
    }
    
    public void setContact(Contact contact) {
        this.contact = contact;
    }
    
    public File getPhotoFile() {
        return photoFile;
    }
    
    public void setPhotoFile(File photoFile) {
        this.photoFile = photoFile;
    }
}
