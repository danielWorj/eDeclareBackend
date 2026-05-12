package com.example.eDeclareback.Service;


import com.example.eDeclareback.Entity.Acte.ActeNaissance;
import com.example.eDeclareback.Utils.ActeGenerator;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ActeService {


    public String generer(ActeNaissance acteNaissance) {
        if (acteNaissance == null) {
            throw new IllegalArgumentException("L'acte de naissance ne peut pas être null.");
        }

        try {
            ActeGenerator generator = new ActeGenerator(acteNaissance);
            String cheminPdf = generator.generer();
            return cheminPdf;
        } catch (IOException e) {
            throw new RuntimeException(
                    "Erreur lors de la génération du PDF pour l'acte n° "
                            + acteNaissance.getNumeroActe(), e);
        }
    }
}