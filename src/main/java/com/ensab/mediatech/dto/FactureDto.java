package com.ensab.mediatech.dto;


import lombok.Data;
import java.util.Date;

@Data
public class FactureDto {
    private Long id;
    private String reference;
    private Date date;
    private Long clientId;

}
