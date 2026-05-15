package com.example.eDeclareback.Controller.Utilisateur;


import com.example.eDeclareback.Entity.Enfant.Sexe;
import com.example.eDeclareback.Entity.Server.ServerReponse;
import com.example.eDeclareback.Entity.Utilisateur.Parent;
import com.example.eDeclareback.Entity.Utilisateur.Utilisateur;
import org.apache.catalina.Server;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/eDeclare/api/user")
@CrossOrigin("*")
public interface UtilisateurControllerInt {

    //Sexe
    @GetMapping("/sexe/all")
    ResponseEntity<List<Sexe>> getAllSexe();

    //PARENT
    @GetMapping("/parent/all")
    ResponseEntity<List<Parent>> findAllParent();
    @GetMapping("/parent/byId/{id}")
    ResponseEntity<Parent> findParentById(@PathVariable Integer id);

    //CRUD AGENT
    @GetMapping("/agent/all")
    ResponseEntity<List<Utilisateur>> getAllUtilisateir();
    @GetMapping("/agent/all/bystructure/{id}")
    ResponseEntity<List<Utilisateur>> getAllUtilisateirByMairie(@PathVariable Integer id);
    @PostMapping("/agent/create")
    ResponseEntity<ServerReponse> createUtilisateur(@RequestParam("user") String utilisateur);
    @PostMapping("/agent/update")
    ResponseEntity<ServerReponse> updateUtilisateur(@RequestParam("user") String utilisateur);
    @GetMapping("/agent/delete/{id}")
    ResponseEntity<ServerReponse> deleteUtilisateur(@PathVariable Integer id);

}
