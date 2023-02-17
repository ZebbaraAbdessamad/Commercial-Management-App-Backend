package com.ensab.mediatech.dto;

import com.ensab.mediatech.annotation.PhoneNumber;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class ClientDto {
    private Long id;
    @NotBlank //le nom ne contien pas espace et non null
    @Size(min = 5 ,max = 20 , message="the number of characters is between 5 et 20 !!")
    private String nom;
    @NotNull(message="This field is required")
    @Size(min = 5 ,max = 20 , message="the number of characters is between 5 et 20 !!")
    private String prenom;

    @NotNull(message="This field is required")
    @PhoneNumber
    private String telephone;

    //@NotNull(message="This field is required")
    private String email;

    @NotNull(message="This field is required")
    private String username;

    @NotNull(message="This field is required")
    private String password;

    private String image;

    private String address;

    private String codePostal;

    private boolean status;



    // @Pattern(regexp ="^(\\+212|0)([ \\-_]*)(\\d[ \\-_]*){9}$",message ="invalid shape" )
}
