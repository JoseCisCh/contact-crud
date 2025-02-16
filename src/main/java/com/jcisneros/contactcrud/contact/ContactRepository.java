package com.jcisneros.contactcrud.contact;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long>{
    @Query("SELECT c FROM Contact c ORDER BY c.id ASC")
    public List<Contact> findAllOrderedById();
}