package com.igor.emprunt_service.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EmpruntDTO {

    private Long id;
    private Long etudiantId;
    private Long livreId;
    private LocalDate dateEmprunt;
    private LocalDate dateRetourPrevue;
    private LocalDate dateRetourEffective;
    private String titrelivre; // Ajouté pour plus d'informations
    private String nomEtudiant; // Ajouté pour plus d'informations

    // Constructeur par défaut
    public EmpruntDTO() {}

    // Constructeur avec tous les champs
    public EmpruntDTO(Long id, Long etudiantId, Long livreId, LocalDate dateEmprunt,
                      LocalDate dateRetourPrevue, LocalDate dateRetourEffective,
                      String titrelivre, String nomEtudiant) {
        this.id = id;
        this.etudiantId = etudiantId;
        this.livreId = livreId;
        this.dateEmprunt = dateEmprunt;
        this.dateRetourPrevue = dateRetourPrevue;
        this.dateRetourEffective = dateRetourEffective;
        this.titrelivre = titrelivre;
        this.nomEtudiant = nomEtudiant;
    }



    // Méthode utilitaire pour vérifier si l'emprunt est en retard
    public boolean estEnRetard() {
        return dateRetourEffective == null && LocalDate.now().isAfter(dateRetourPrevue);
    }

    @Override
    public String toString() {
        return "EmpruntDTO{" +
                "id=" + id +
                ", etudiantId=" + etudiantId +
                ", livreId=" + livreId +
                ", dateEmprunt=" + dateEmprunt +
                ", dateRetourPrevue=" + dateRetourPrevue +
                ", dateRetourEffective=" + dateRetourEffective +
                ", titrelivre='" + titrelivre + '\'' +
                ", nomEtudiant='" + nomEtudiant + '\'' +
                '}';
    }
}