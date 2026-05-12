package com.example.eDeclareback.Repository.Piece;


import com.example.eDeclareback.Entity.Domaine.Etablissement;
import com.example.eDeclareback.Entity.PieceJustificative.TypePiece;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TypePieceRepository extends JpaRepository<TypePiece,Integer> {
    List<TypePiece> findByStructure(Etablissement structure);

}
