package com.ensab.mediatech.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Profile implements Serializable {
    @Id   @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String bibliography;
    private Date dateOfBirth;
    private String websiteUrl;
    private String location;
    private String occupation;
    private String interests;

    @OneToOne
    @JoinColumn(nullable = false)
    private User user;
}
