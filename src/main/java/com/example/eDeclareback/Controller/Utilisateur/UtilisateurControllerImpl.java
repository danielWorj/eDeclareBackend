package com.example.eDeclareback.Controller.Utilisateur;


import com.example.eDeclareback.Entity.Enfant.Sexe;
import com.example.eDeclareback.Repository.Enfant.SexeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UtilisateurControllerImpl implements UtilisateurControllerInt {
    @Autowired
    private SexeRepository sexeRepository;
    @Override
    public ResponseEntity<List<Sexe>> getAllSexe() {
        return ResponseEntity.ok(this.sexeRepository.findAll());
    }
}
