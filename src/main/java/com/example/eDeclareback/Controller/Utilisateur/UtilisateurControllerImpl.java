package com.example.eDeclareback.Controller.Utilisateur;


import com.example.eDeclareback.Entity.Enfant.Sexe;
import com.example.eDeclareback.Entity.Utilisateur.Parent;
import com.example.eDeclareback.Repository.Enfant.SexeRepository;
import com.example.eDeclareback.Repository.Utilisateur.ParentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UtilisateurControllerImpl implements UtilisateurControllerInt {
    @Autowired
    private SexeRepository sexeRepository;
    @Autowired private ParentRepository parentRepository;
    @Override
    public ResponseEntity<List<Sexe>> getAllSexe() {
        return ResponseEntity.ok(this.sexeRepository.findAll());
    }

    @Override
    public ResponseEntity<List<Parent>> findAllParent() {
        return ResponseEntity.ok(this.parentRepository.findAll());
    }

    @Override
    public ResponseEntity<Parent> findParentById(Integer id) {
        return ResponseEntity.ok(
                this.parentRepository.findById(id).orElse(null)
        );
    }
}
