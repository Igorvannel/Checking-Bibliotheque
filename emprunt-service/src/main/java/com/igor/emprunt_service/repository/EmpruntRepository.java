package com.igor.emprunt_service.repository;


import com.igor.emprunt_service.model.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {

    // Trouver tous les emprunts d'un étudiant spécifique
    List<Emprunt> findByEtudiantId(Long etudiantId);

    // Trouver tous les emprunts en cours (non retournés)
    List<Emprunt> findByDateRetourEffectiveIsNull();

    // Trouver tous les emprunts en retard
    List<Emprunt> findByDateRetourPrevueBefore(LocalDate date);

    // Trouver tous les emprunts pour un livre spécifique
    List<Emprunt> findByLivreId(Long livreId);

    // Compter le nombre d'emprunts en cours pour un étudiant
    long countByEtudiantIdAndDateRetourEffectiveIsNull(Long etudiantId);

    // Trouver le dernier emprunt d'un livre spécifique
    Emprunt findTopByLivreIdOrderByDateEmpruntDesc(Long livreId);

    // Trouver tous les emprunts entre deux dates
    List<Emprunt> findByDateEmpruntBetween(LocalDate dateDebut, LocalDate dateFin);
}