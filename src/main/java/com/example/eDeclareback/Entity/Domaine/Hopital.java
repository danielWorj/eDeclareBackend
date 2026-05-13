package com.example.eDeclareback.Entity.Domaine;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "hopital")
@Data
@DiscriminatorValue("HOPITAL") // ← corrigé (était "mairie")
@PrimaryKeyJoinColumn(name = "id") // ← ajouté
public class Hopital extends Structure {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mairie_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "hopitaux"})
    private Mairie mairie;
}