package com.example.eDeclareback.Repository.Piece;


import com.example.eDeclareback.Entity.PieceJustificative.PieceJustificative;
import com.example.eDeclareback.Entity.PieceJustificative.TypePiece;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PieceJustificativeRepository extends JpaRepository<PieceJustificative, Integer> {
    List<PieceJustificative> findByType(TypePiece typePiece);
}
