package com.ensab.mediatech.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Produit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String reference;
    @Column(nullable = false)
    private String libelle;
    @Column(nullable = false)
    private Double prix;
    @Column(nullable = false)
    private Integer quantiteStock;

    @OneToMany(mappedBy = "produit")
    private Collection<LigneFacture> ligneFacture ;
}
