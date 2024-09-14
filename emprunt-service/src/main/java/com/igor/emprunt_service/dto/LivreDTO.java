package com.igor.emprunt_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LivreDTO {

    private Long id;
    private String titre;
    private String auteur;
    private String programmeEtudes;

    // Autres propriétés et méthodes si nécessaires
}
