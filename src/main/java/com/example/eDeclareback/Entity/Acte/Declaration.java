package com.example.eDeclareback.Entity.Acte;


import com.example.eDeclareback.Entity.Domaine.Hopital;
import com.example.eDeclareback.Entity.Domaine.Mairie;
import com.example.eDeclareback.Entity.Enfant.Enfant;
import com.example.eDeclareback.Entity.PieceJustificative.PieceJustificative;
import com.example.eDeclareback.Entity.Utilisateur.Parent;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "declaration")  // ← ajoute le name
@Data
public class Declaration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate date;

    @ManyToOne(fetch = FetchType.EAGER)  // ← LAZY → EAGER
    @JoinColumn(name = "hopital_id")     // ← ajoute JoinColumn
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "hopitaux", "mairie"})
    private Hopital hopital;

    @ManyToOne(fetch = FetchType.EAGER)  // ← LAZY → EAGER
    @JoinColumn(name = "mairie_id")      // ← ajoute JoinColumn
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "hopitaux"})
    private Mairie mairie;

    @ManyToOne(fetch = FetchType.EAGER)  // ← LAZY → EAGER
    @JoinColumn(name = "enfant_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Enfant enfant;

    @ManyToOne(fetch = FetchType.EAGER)  // ← LAZY → EAGER
    @JoinColumn(name = "mere_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Parent mere;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY)
    private List<PieceJustificative> pieceJustificatives;
}