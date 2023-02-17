package com.ensab.mediatech.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LigneFacture implements Serializable {
    @Id   @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Produit produit;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Facture facture;
    private Integer quantite;


}
