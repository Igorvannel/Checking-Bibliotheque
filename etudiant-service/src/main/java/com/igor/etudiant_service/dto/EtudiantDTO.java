package com.igor.etudiant_service.dto;


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
    private String prenom;
    private Long etudiantId;
    private String programmeEtudes;
}
