package com.ensab.mediatech.dto;


import lombok.Data;

@Data
public class LigneFactureDto {
    private Long id;
    private Long factureId;
    private Long produitId;
    private Integer quantite;

}
