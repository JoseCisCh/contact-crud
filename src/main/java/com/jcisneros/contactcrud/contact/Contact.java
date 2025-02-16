package com.jcisneros.contactcrud.contact;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Data
@Entity
public class Contact {
    @Id
    @SequenceGenerator(name = "id_sequence",
                        sequenceName = "id_sequence",
                        allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE ,generator = "id_sequence")
    private Long id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNum;
    private String address;
    private String imageUrl;
    
    
    public Contact(String firstName, String lastName, String emailAddress, String phoneNum, String address,
    String imageUrl) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.phoneNum = phoneNum;
        this.address = address;
        this.imageUrl = imageUrl;
    }
    
    
    public Contact() {
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName != null ? firstName : "";
    }
    
    
    public void setLastName(String lastName) {
        this.lastName = lastName != null ? lastName : "";
    }
    
    
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress != null ? emailAddress : "";
    }
    
    public String getFirstName() {
        return firstName != null ? firstName : "";
    }


    public String getLastName() {
        return lastName != null ? lastName : "";
    }


    public String getEmailAddress() {
        return emailAddress != null ? emailAddress : "";
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}
