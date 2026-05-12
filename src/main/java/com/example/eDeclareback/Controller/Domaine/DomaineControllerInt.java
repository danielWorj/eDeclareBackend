package com.example.eDeclareback.Controller.Domaine;

import com.example.eDeclareback.Entity.Domaine.Hopital;
import com.example.eDeclareback.Entity.Domaine.Mairie;
import com.example.eDeclareback.Entity.Server.ServerReponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/eDeclare/api/domaine")
@CrossOrigin("*")
public interface DomaineControllerInt {

    // ═══════════════════════════
    //  MAIRIE
    // ═══════════════════════════

    @GetMapping("/mairie/all")
    ResponseEntity<List<Mairie>> findAllMairie();

    @GetMapping("/mairie/byId/{id}")
    ResponseEntity<Mairie> findMairieById(@PathVariable Integer id);

    @PostMapping("/mairie/create")
    ResponseEntity<ServerReponse> createMairie(@RequestParam("mairie") String mairie);

    @PostMapping("/mairie/update")
    ResponseEntity<ServerReponse> updateMairie(@RequestParam("mairie") String mairie);

    @DeleteMapping("/mairie/delete/{id}")
    ResponseEntity<ServerReponse> deleteMairie(@PathVariable Integer id);

    // ═══════════════════════════
    //  HOPITAL
    // ═══════════════════════════

    @GetMapping("/hopital/all")
    ResponseEntity<List<Hopital>> findAllHopital();

    @GetMapping("/hopital/byId/{id}")
    ResponseEntity<Hopital> findHopitalById(@PathVariable Integer id);

    @GetMapping("/hopital/bymairie/{mairieId}")
    ResponseEntity<List<Hopital>> findHopitalByMairie(@PathVariable Integer mairieId);

    @PostMapping("/hopital/create")
    ResponseEntity<ServerReponse> createHopital(@RequestParam("hopital") String hopital);

    @PostMapping("/hopital/update")
    ResponseEntity<ServerReponse> updateHopital(@RequestParam("hopital") String hopital);

    @DeleteMapping("/hopital/delete/{id}")
    ResponseEntity<ServerReponse> deleteHopital(@PathVariable Integer id);
}