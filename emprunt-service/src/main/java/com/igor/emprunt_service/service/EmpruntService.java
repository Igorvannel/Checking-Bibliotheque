package com.igor.emprunt_service.service;

import com.igor.emprunt_service.dto.EmpruntDTO;
import com.igor.emprunt_service.dto.EtudiantDTO;
import com.igor.emprunt_service.dto.LivreDTO;
import com.igor.emprunt_service.exception.EmpruntNotFoundException;
import com.igor.emprunt_service.exception.LivreNonDisponibleException;
import com.igor.emprunt_service.model.Emprunt;
import com.igor.emprunt_service.repository.EmpruntRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmpruntService {

    @Autowired
    private final EmpruntRepository empruntRepository;
    @Autowired
    private final RestTemplate restTemplate;

    // URLs des services externes
    private final String etudiantServiceUrl = "http://localhost:7002/etudiants/";
    private final String catalogueServiceUrl = "http://localhost:7001/livres/";

    @Transactional
    public EmpruntDTO creerEmprunt(EmpruntDTO empruntDTO) {
        if (!verifierEligibiliteEmprunt(empruntDTO.getEtudiantId(), empruntDTO.getLivreId())) {
            throw new LivreNonDisponibleException("L'étudiant n'est pas éligible pour emprunter ce livre.");
        }

        Emprunt emprunt = new Emprunt();
        emprunt.setEtudiantId(empruntDTO.getEtudiantId());
        emprunt.setLivreId(empruntDTO.getLivreId());
        emprunt.setDateEmprunt(LocalDate.now());
        emprunt.setDateRetourPrevue(LocalDate.now().plusDays(14)); // Par exemple, 14 jours d'emprunt

        Emprunt savedEmprunt = empruntRepository.save(emprunt);
        return convertToDTO(savedEmprunt);
    }

    public EmpruntDTO obtenirEmprunt(Long id) {
        Emprunt emprunt = empruntRepository.findById(id)
                .orElseThrow(() -> new EmpruntNotFoundException("Emprunt non trouvé avec l'id: " + id));
        return convertToDTO(emprunt);
    }

    public List<EmpruntDTO> obtenirEmpruntsParEtudiant(Long etudiantId) {
        List<Emprunt> emprunts = empruntRepository.findByEtudiantId(etudiantId);
        return emprunts.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public EmpruntDTO retournerLivre(Long id) {
        Emprunt emprunt = empruntRepository.findById(id)
                .orElseThrow(() -> new EmpruntNotFoundException("Emprunt non trouvé avec l'id: " + id));
        emprunt.setDateRetourEffective(LocalDate.now());
        Emprunt updatedEmprunt = empruntRepository.save(emprunt);
        return convertToDTO(updatedEmprunt);
    }

    public boolean verifierEligibiliteEmprunt(Long etudiantId, Long livreId) {
        // Récupérer les informations sur l'étudiant
        EtudiantDTO etudiant = restTemplate.getForObject(etudiantServiceUrl + etudiantId, EtudiantDTO.class);

        // Récupérer les informations sur le livre
        LivreDTO livre = restTemplate.getForObject(catalogueServiceUrl + livreId, LivreDTO.class);

        if (etudiant != null && livre != null) {
            return livre.getProgrammeEtudes().equalsIgnoreCase(etudiant.getProgrammeEtudes());
        }
        return false;
    }

    public List<EmpruntDTO> obtenirEmpruntsEnRetard() {
        LocalDate now = LocalDate.now();
        List<Emprunt> empruntsEnRetard = empruntRepository.findByDateRetourPrevueBefore(now);
        return empruntsEnRetard.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private EmpruntDTO convertToDTO(Emprunt emprunt) {
        EmpruntDTO dto = new EmpruntDTO();
        dto.setId(emprunt.getId());
        dto.setEtudiantId(emprunt.getEtudiantId());
        dto.setLivreId(emprunt.getLivreId());
        dto.setDateEmprunt(emprunt.getDateEmprunt());
        dto.setDateRetourPrevue(emprunt.getDateRetourPrevue());
        dto.setDateRetourEffective(emprunt.getDateRetourEffective());
        return dto;
    }
}
