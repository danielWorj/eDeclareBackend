package com.example.eDeclareback.Controller.Utilisateur;


import com.example.eDeclareback.Dto.Utilisateur.UtilisateurDto;
import com.example.eDeclareback.Entity.Enfant.Sexe;
import com.example.eDeclareback.Entity.Server.ServerReponse;
import com.example.eDeclareback.Entity.Utilisateur.Parent;
import com.example.eDeclareback.Entity.Utilisateur.RoleUser;
import com.example.eDeclareback.Entity.Utilisateur.StatutUser;
import com.example.eDeclareback.Entity.Utilisateur.Utilisateur;
import com.example.eDeclareback.Entity.Domaine.Structure;
import com.example.eDeclareback.Repository.Domaine.StructureHRepository;
import com.example.eDeclareback.Repository.Domaine.StructureRepository;
import com.example.eDeclareback.Repository.Enfant.SexeRepository;
import com.example.eDeclareback.Repository.Utilisateur.ParentRepository;
import com.example.eDeclareback.Repository.Utilisateur.RoleUserRepository;
import com.example.eDeclareback.Repository.Utilisateur.StatutUserRepository;
import com.example.eDeclareback.Repository.Utilisateur.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class UtilisateurControllerImpl implements UtilisateurControllerInt {

    @Autowired
    private SexeRepository sexeRepository;

    @Autowired
    private ParentRepository parentRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private StatutUserRepository statutUserRepository;

    @Autowired
    private RoleUserRepository roleUserRepository;

    @Autowired
    private StructureHRepository structureRepository;

    @Autowired
    private ObjectMapper objectMapper;

    // ─── Sexe ───────────────────────────────────────────────────────────────

    @Override
    public ResponseEntity<List<Sexe>> getAllSexe() {
        return ResponseEntity.ok(this.sexeRepository.findAll());
    }

    // ─── Parent ──────────────────────────────────────────────────────────────

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

    @Override
    public ResponseEntity<List<Utilisateur>> getAllUtilisateir() {
        return ResponseEntity.ok(this.utilisateurRepository.findAll());
    }

    // ─── CRUD Utilisateur (Agent) ─────────────────────────────────────────────

    /**
     * Retourne tous les utilisateurs rattachés à une structure donnée (mairie ou hôpital).
     */
    @Override
    public ResponseEntity<List<Utilisateur>> getAllUtilisateirByMairie(Integer id) {
        try {
            List<Utilisateur> utilisateurs = utilisateurRepository.findByStructure(
                    this.structureRepository.findById(id).orElse(null)
            );
            return ResponseEntity.ok(utilisateurs);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Crée un nouvel utilisateur à partir d'un JSON passé en paramètre.
     * Le champ password_hash du DTO est stocké directement dans password.
     * La date de création est positionnée automatiquement.
     */
    @Override
    public ResponseEntity<ServerReponse> createUtilisateur(String utilisateur) {
        try {
            UtilisateurDto dto = objectMapper.readValue(utilisateur, UtilisateurDto.class);

            // Vérification doublons (nom + prénom + téléphone)
            Optional<Utilisateur> existant = utilisateurRepository
                    .findByNomAndPrenomAndTelephone(dto.getNom(), dto.getPrenom(), dto.getTelephone());
            if (existant.isPresent()) {
                return ResponseEntity.badRequest().body(
                        new ServerReponse("Un utilisateur avec ce nom, prénom et téléphone existe déjà.",false)
                );
            }

            Utilisateur nouvelUtilisateur = new Utilisateur();
            nouvelUtilisateur.setNom(dto.getNom());
            nouvelUtilisateur.setPrenom(dto.getPrenom());
            nouvelUtilisateur.setTelephone(dto.getTelephone());
            nouvelUtilisateur.setEmail(dto.getEmail());
            //nouvelUtilisateur.setPassword(dto.getPassword_hash());
            nouvelUtilisateur.setPassword("test");
            nouvelUtilisateur.setCreation(LocalDate.now());
            nouvelUtilisateur.setModification(LocalDate.now());

            // Relations
            if (dto.getStatutUser() != null) {
                StatutUser statut = statutUserRepository.findById(dto.getStatutUser()).orElse(null);
                nouvelUtilisateur.setStatutUser(statut);
            }
            if (dto.getRoleUser() != null) {
                RoleUser role = roleUserRepository.findById(dto.getRoleUser()).orElse(null);
                nouvelUtilisateur.setRoleUser(role);
            }
            if (dto.getStructure() != null) {
                Structure structure = structureRepository.findById(dto.getStructure()).orElse(null);
                nouvelUtilisateur.setStructure(structure);
            }

            utilisateurRepository.save(nouvelUtilisateur);
            return ResponseEntity.ok(new ServerReponse(true, "Utilisateur créé avec succès."));

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(
                    new ServerReponse(false, "Erreur lors de la création : " + e.getMessage())
            );
        }
    }

    /**
     * Met à jour un utilisateur existant identifié par son id dans le DTO.
     * Seuls les champs non-null du DTO sont mis à jour.
     */
    @Override
    public ResponseEntity<ServerReponse> updateUtilisateur(String utilisateur) {
        try {
            UtilisateurDto dto = objectMapper.readValue(utilisateur, UtilisateurDto.class);

            if (dto.getId() == null) {
                return ResponseEntity.badRequest().body(
                        new ServerReponse(false, "L'identifiant de l'utilisateur est requis pour la mise à jour.")
                );
            }

            Optional<Utilisateur> optUtilisateur = utilisateurRepository.findById(dto.getId());
            if (optUtilisateur.isEmpty()) {
                return ResponseEntity.badRequest().body(
                        new ServerReponse(false, "Utilisateur introuvable avec l'id : " + dto.getId())
                );
            }

            Utilisateur existant = optUtilisateur.get();

            if (dto.getNom() != null)       existant.setNom(dto.getNom());
            if (dto.getPrenom() != null)    existant.setPrenom(dto.getPrenom());
            if (dto.getTelephone() != null) existant.setTelephone(dto.getTelephone());
            if (dto.getEmail() != null)     existant.setEmail(dto.getEmail());
            if (dto.getPassword_hash() != null) existant.setPassword(dto.getPassword_hash());
            existant.setModification(LocalDate.now());

            if (dto.getStatutUser() != null) {
                StatutUser statut = statutUserRepository.findById(dto.getStatutUser()).orElse(null);
                existant.setStatutUser(statut);
            }
            if (dto.getRoleUser() != null) {
                RoleUser role = roleUserRepository.findById(dto.getRoleUser()).orElse(null);
                existant.setRoleUser(role);
            }
            if (dto.getStructure() != null) {
                Structure structure = structureRepository.findById(dto.getStructure()).orElse(null);
                existant.setStructure(structure);
            }

            utilisateurRepository.save(existant);
            return ResponseEntity.ok(new ServerReponse(true, "Utilisateur mis à jour avec succès."));

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(
                    new ServerReponse(false, "Erreur lors de la mise à jour : " + e.getMessage())
            );
        }
    }

    /**
     * Supprime (ou désactive) un utilisateur par son id.
     */
    @Override
    public ResponseEntity<ServerReponse> deleteUtilisateur(Integer id) {
        try {
            Optional<Utilisateur> optUtilisateur = utilisateurRepository.findById(id);
            if (optUtilisateur.isEmpty()) {
                return ResponseEntity.badRequest().body(
                        new ServerReponse(false, "Utilisateur introuvable avec l'id : " + id)
                );
            }

            utilisateurRepository.deleteById(id);
            return ResponseEntity.ok(new ServerReponse(true, "Utilisateur supprimé avec succès."));

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(
                    new ServerReponse(false, "Erreur lors de la suppression : " + e.getMessage())
            );
        }
    }
}