package com.example.eDeclareback.Repository.Acte;

import com.example.eDeclareback.Entity.Acte.Declaration;
import com.example.eDeclareback.Entity.Domaine.Hopital;
import com.example.eDeclareback.Entity.Domaine.Mairie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeclarationRepository extends JpaRepository<Declaration,Integer> {
    List<Declaration> findByHopital(Hopital hopital);
    List<Declaration> findByMairie(Mairie mairie);
}
