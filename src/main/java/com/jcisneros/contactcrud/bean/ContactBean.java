package com.jcisneros.contactcrud.bean;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jcisneros.contactcrud.contact.Contact;
import com.jcisneros.contactcrud.contact.ContactRepository;

import java.io.Serializable;
import java.util.List;

@Component("contactBean")
@ViewScoped
public class ContactBean implements Serializable {
    
    
    
    @Autowired
    private ContactRepository contactRepository;

    List<Contact> allContacts;
    List<Contact> filteredContacts;
    private Contact contact;
    private String rowsPerPageTemplate;
    private Long contactToDelete;
    
    
    
    @PostConstruct
    public void init() {
        contact = new Contact();
        this.allContacts = contactRepository.findAllOrderedById();
        
        
        rowsPerPageTemplate = "5, 10";
        
        if(allContacts.size() > 10 ) {
            rowsPerPageTemplate += "," + allContacts.size();
        }
    }
    
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }
    
    public void handleDelete() {
        contactRepository.deleteById(contactToDelete);
    }
    
    public String goToUpdate(Long contactId) {
        return "/views/edit-contact.xhtml?faces-redirect=true&contactId=" + contactId;
    }
    
    public Contact getContact() {
        return this.contact;
    }
    
    public void setContact(Contact contact) {
        this.contact = contact;
    }
    
    public List<Contact> getFilteredContacts() {
        return this.filteredContacts;
    }
    
    public void setFilteredContacts(List<Contact> filteredContacts) {
        this.filteredContacts = filteredContacts;
    }
    
    
    public String getRowsPerPageTemplate() {
        return rowsPerPageTemplate;
    }
    
    public void setRowsPerPageTemplate(String rowsPerPageTemplate) {
        this.rowsPerPageTemplate = rowsPerPageTemplate;
    }

    public Long getContactToDelete() {
        return contactToDelete;
    }
    
    public void setContactToDelete(Long contactToDelete) {
        this.contactToDelete = contactToDelete;
    }
}