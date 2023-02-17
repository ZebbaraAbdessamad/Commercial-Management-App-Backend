package com.ensab.mediatech.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Facture  implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String reference;
    @Column(nullable = false , name = "created_date_facture")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "client_id",nullable = false)
    private Client client;

    @OneToMany(mappedBy = "facture")
    private Collection<LigneFacture> ligneFacture ;



}
