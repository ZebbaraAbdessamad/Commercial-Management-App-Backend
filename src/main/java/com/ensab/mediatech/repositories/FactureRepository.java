package com.ensab.mediatech.repositories;

import com.ensab.mediatech.entities.Facture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FactureRepository extends JpaRepository<Facture,Long> {

}
