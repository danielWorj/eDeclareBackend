package com.example.eDeclareback.Repository.Acte;

import com.example.eDeclareback.Entity.Acte.ActeNaissance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ActeNaissanceRepository extends JpaRepository<ActeNaissance,Integer> {

    @Query(value = "SELECT a FROM ActeNaissance a JOIN a.declaration d JOIN d.mairie m WHERE m.id=:id")
    List<ActeNaissance> findAllActeByMairie(@Param("id") Integer id);
}
