package com.igor.catalogue_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EtudiantDTO {

    private Long id;
    private String nom;
    private String programmeEtudes;

    // Autres propriétés et méthodes si nécessaires
}
