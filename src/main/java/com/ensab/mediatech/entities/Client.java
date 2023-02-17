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
public class Client extends User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @OrderColumn(name = "id", nullable = false)
    private Long id;

    @OrderColumn(name = "address", nullable = false)
    private String address;

    @OrderColumn(name = "codePostal", nullable = false)
    private String codePostal;

    private boolean status;

    @OneToMany(mappedBy = "client" , cascade = CascadeType.ALL ,fetch = FetchType.LAZY)
    private Collection<Facture> factures;

}
