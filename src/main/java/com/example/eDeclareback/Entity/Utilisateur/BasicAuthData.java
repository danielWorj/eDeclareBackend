package com.example.eDeclareback.Entity.Utilisateur;

import lombok.Data;


@Data
public class BasicAuthData {
    private Integer id;
    private Integer role;
    private Integer etablissement;

    public BasicAuthData(Integer id, Integer role, Integer etablissement) {
        this.id = id;
        this.role = role;
        this.etablissement = etablissement;
    }


}
