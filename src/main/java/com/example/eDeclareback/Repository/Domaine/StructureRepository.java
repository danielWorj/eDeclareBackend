package com.example.eDeclareback.Repository.Domaine;

import com.example.eDeclareback.Entity.Domaine.Etablissement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StructureRepository extends JpaRepository<Etablissement,Integer> {

}
