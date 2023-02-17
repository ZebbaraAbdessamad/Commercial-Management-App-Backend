package com.ensab.mediatech.repositories;

import com.ensab.mediatech.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
    Client findByNom(String nom);
}
