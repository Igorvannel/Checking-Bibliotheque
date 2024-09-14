package com.igor.etudiant_service.service;

import com.igor.etudiant_service.dto.EtudiantDTO;
import com.igor.etudiant_service.exception.EtudiantNotFoundException;
import com.igor.etudiant_service.model.Etudiant;
import com.igor.etudiant_service.repository.EtudiantRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@AllArgsConstructor
public class EtudiantService {

    @Autowired
    private final EtudiantRepository etudiantRepository;

    @Transactional
    public EtudiantDTO enregistrerEtudiant(EtudiantDTO etudiantDTO) {
        Etudiant etudiant = new Etudiant();
        etudiant.setNom(etudiantDTO.getNom());
        etudiant.setPrenom(etudiantDTO.getPrenom());
        etudiant.setEtudiantId(etudiantDTO.getEtudiantId());
        etudiant.setProgrammeEtudes(etudiantDTO.getProgrammeEtudes());

        Etudiant savedEtudiant = etudiantRepository.save(etudiant);
        return convertToDTO(savedEtudiant);
    }

    @Transactional
    public EtudiantDTO mettreAJourEtudiant(Long id, EtudiantDTO etudiantDTO) {
        Etudiant etudiant = etudiantRepository.findById(id)
                .orElseThrow(() -> new EtudiantNotFoundException("Étudiant non trouvé avec l'id: " + id));

        etudiant.setNom(etudiantDTO.getNom());
        etudiant.setPrenom(etudiantDTO.getPrenom());
        etudiant.setEtudiantId(etudiantDTO.getEtudiantId());
        etudiant.setProgrammeEtudes(etudiantDTO.getProgrammeEtudes());

        Etudiant updatedEtudiant = etudiantRepository.save(etudiant);
        return convertToDTO(updatedEtudiant);
    }

    public EtudiantDTO obtenirEtudiantParCode(Long etudiantId) {
        Etudiant etudiant = etudiantRepository.findByEtudiantId(etudiantId)
                .orElseThrow(() -> new EtudiantNotFoundException("Étudiant non trouvé avec le code: " + etudiantId));
        return convertToDTO(etudiant);
    }

    private EtudiantDTO convertToDTO(Etudiant etudiant) {
        EtudiantDTO dto = new EtudiantDTO();
        dto.setId(etudiant.getId());
        dto.setNom(etudiant.getNom());
        dto.setPrenom(etudiant.getPrenom());
        dto.setEtudiantId(etudiant.getEtudiantId());
        dto.setProgrammeEtudes(etudiant.getProgrammeEtudes());
        return dto;
    }
}
