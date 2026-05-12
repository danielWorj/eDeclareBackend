package com.example.eDeclareback.Controller.Utilisateur;


import com.example.eDeclareback.Entity.Enfant.Sexe;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/eDeclare/api/user")
@CrossOrigin("*")
public interface UtilisateurControllerInt {

    //Sexe
    @GetMapping("/sexe/all")
    ResponseEntity<List<Sexe>> getAllSexe();
}
