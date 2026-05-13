package com.example.eDeclareback.Entity.Domaine;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "mairie")          // ← ajouté
@Data
@DiscriminatorValue("MAIRIE")    // ← majuscules comme en base
@PrimaryKeyJoinColumn(name = "id") // ← ajouté
public class Mairie extends Structure {

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "mairie") // ← mappedBy ajouté
    private List<Hopital> hopitaux;
}