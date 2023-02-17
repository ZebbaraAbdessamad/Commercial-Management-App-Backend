package com.ensab.mediatech.repositories;

import com.ensab.mediatech.entities.LigneFacture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface LigneFactureRepository extends JpaRepository<LigneFacture ,Long> {

}
