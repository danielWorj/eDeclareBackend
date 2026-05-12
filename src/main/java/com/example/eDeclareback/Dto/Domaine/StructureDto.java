package com.example.eDeclareback.Dto.Domaine;

import lombok.Data;

import java.time.LocalDate;

@Data
public class StructureDto {
    private Integer id;
    private String nom ;
    private String telephone ;
    private String localisation ;
    private String email ;
    private Boolean actif ;
    private LocalDate creation;
    private Integer type ;
}
