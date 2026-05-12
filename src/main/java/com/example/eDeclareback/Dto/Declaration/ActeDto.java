package com.example.eDeclareback.Dto.Declaration;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ActeDto {
    private Integer id ;
    private LocalDate date ;

    //Informatiosn sur la declaration
    private Integer declaration;

    //Informations sur le pere
    private String nomPere ;
    private String prenomPere ;
    private String telephonePere ;
    private String emailPere ;
    private String profession ;
    private String domicile ;
    private String dateNaissance ;
    private String lieuNaissance ;

    // Liste des types de pièces jointes (IDs des TypePieceDeclaration)
    // Les fichiers sont transmis séparément via MultipartFile[]
    private List<Integer> typesPiecesJointes;
}
