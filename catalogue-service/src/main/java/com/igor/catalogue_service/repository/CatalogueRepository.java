package com.igor.catalogue_service.repository;

import com.igor.catalogue_service.model.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatalogueRepository extends JpaRepository<Livre, Long> {

    List<Livre> findByProgrammeEtudes(String programmeEtudes);
    List<Livre> findByTitreContainingIgnoreCase(String titre);
}