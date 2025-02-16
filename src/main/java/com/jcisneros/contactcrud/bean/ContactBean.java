package com.jcisneros.contactcrud.bean;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.jcisneros.contactcrud.contact.Contact;
import com.jcisneros.contactcrud.contact.ContactRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

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
    
    public void save() {
        contactRepository.save(contact);
        contact = new Contact();
    }
    
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }
    
    public void handleDelete() {
        contactRepository.deleteById(contactToDelete);
        this.allContacts = contactRepository.findAllOrderedById();
        this.filteredContacts = null;
        
        PrimeFaces.current().ajax().update("form, :formTable:customersData, :formTable, :customersData, formTable, customersData,:nameColumn, nameColumn");
    }
    
    public void updateMessage() {
        System.out.println("Updating message");
    }
    
    public String goToUpdate(Long contactId) {
        return "/views/updateContact.xhtml?faces-redirect=true&contactId=" + contactId;
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
    
    public boolean filterContactsByName(Object value, Object filter, Locale locale) {
        
        if(value == null || value.toString().trim().isEmpty()) return false;
        
        if (filter == null || filter.toString().trim().isEmpty()) {
            return true;
        }
        // Implement custom filtering logic here
        String filterText = filter.toString().trim().toLowerCase();
        
        String currentValue = (String) value.toString().trim().toLowerCase();
        
        return currentValue.contains(filterText);
    }
    
    public boolean filterContactsByEmail(Object value, Object filter, Locale locale) {
        
        if(value == null || value.toString().trim().isEmpty()) return false;
        
        if (filter == null || filter.toString().trim().isEmpty()) {
            return true;
        }
        // Implement custom filtering logic here
        String filterText = filter.toString().trim().toLowerCase();
        
        String currentValue = (String) value.toString().trim().toLowerCase();
        
        return currentValue.contains(filterText) ||
        contact.getEmailAddress().toLowerCase().contains(filterText);
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