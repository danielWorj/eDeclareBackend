package com.example.eDeclareback.Dto.Utilisateur;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UtilisateurDto {
    private Integer id ;
    private String nom ;
    private String prenom ;
    private String telephone ;
    private String email ;
    private String password_hash ;
    private LocalDate creation ;
    private LocalDate modification ;
    private Integer statutUser ;
    private Integer roleUser ;
    private Integer structure ;
}
