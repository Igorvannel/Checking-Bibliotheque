package com.igor.etudiant_service.repository;


import com.igor.etudiant_service.model.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
    Optional<Etudiant> findByEtudiantId(Long etudiantId);
}
