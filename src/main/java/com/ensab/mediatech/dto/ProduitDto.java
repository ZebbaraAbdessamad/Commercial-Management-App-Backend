package com.ensab.mediatech.dto;

import lombok.Data;
import javax.validation.constraints.NotNull;


@Data
public class ProduitDto {

    private Long id;

    @NotNull(message="This field is required")
    private String reference;

    @NotNull(message="This field is required")
    private String libelle;

    @NotNull(message="This field is required")
    private Double prix;

    @NotNull(message="This field is required")
    private Integer quantiteStock;

}
