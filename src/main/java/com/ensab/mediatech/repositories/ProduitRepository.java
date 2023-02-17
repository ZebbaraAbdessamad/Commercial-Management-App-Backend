package com.ensab.mediatech.repositories;

import com.ensab.mediatech.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProduitRepository  extends JpaRepository<Produit ,Long> {
    //On utilse Optional<Produit> pour l'utilisation des methodes orElsethrow oubien orElse .
    Optional<Produit> findByReference(String reference);
    Optional<Produit> findByLibelle(String libelle);
    List<Produit> findByPrixGreaterThan(BigDecimal value);
}
