package com.jcisneros.contactcrud.bean;

import java.io.Serializable;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.annotation.ManagedProperty;
import javax.faces.component.FacesComponent;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import com.jcisneros.contactcrud.contact.Contact;
import com.jcisneros.contactcrud.contact.ContactRepository;

@Component("updateContactBean")
@ViewScoped
public class UpdateContactBean implements Serializable {


@Autowired
ContactRepository contactRepository;

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
        contactRepository.save(this.contact);
        return "/views/customers.xhtml?faces-redirect=true";
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
