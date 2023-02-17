package com.ensab.mediatech.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public  abstract class User implements Serializable{
    @Id   @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

   // @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String telephone;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String image;


    @ManyToMany(mappedBy = "users" , fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();

    @OneToOne(mappedBy = "user" ,fetch = FetchType.LAZY)
    private Profile profile;

}
