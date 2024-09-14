package com.igor.catalogue_service.service;

import com.igor.catalogue_service.dto.EtudiantDTO;
import com.igor.catalogue_service.exception.LivreNotFoundException;
import com.igor.catalogue_service.exception.LivreAlreadyExistsException;
import com.igor.catalogue_service.model.Livre;
import com.igor.catalogue_service.repository.CatalogueRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CatalogueService {

    @Autowired
    private CatalogueRepository catalogueRepository;
    private final RestTemplate restTemplate;

    // URL du service étudiant
    private final String etudiantServiceUrl = "http://localhost:7002/etudiants/";

    public Livre saveLivre(Livre livre) {
        // Vérifiez si un livre avec le même ID existe déjà
        if (livre.getId() != null && catalogueRepository.existsById(livre.getId())) {
            throw new LivreAlreadyExistsException(livre.getTitre());
        }
        return catalogueRepository.save(livre);
    }

    public Optional<Livre> getLivreById(Long id) {
        // Lancer une exception si le livre n'est pas trouvé
        return Optional.ofNullable(catalogueRepository.findById(id)
                .orElseThrow(() -> new LivreNotFoundException(id)));
    }

    public List<Livre> getAllLivres() {
        return catalogueRepository.findAll();
    }

    public List<Livre> getLivresByProgramme(String programmeEtudes) {
        return catalogueRepository.findByProgrammeEtudes(programmeEtudes);
    }

    public List<Livre> searchLivres(String titre) {
        return catalogueRepository.findByTitreContainingIgnoreCase(titre);
    }

    public boolean verifierLivreProgramme(Long etudiantId, Long livreId) {
        // Appel au service étudiant pour obtenir les informations sur l'étudiant
        EtudiantDTO etudiant = restTemplate.getForObject(etudiantServiceUrl + etudiantId, EtudiantDTO.class);

        // Obtenir les informations sur le livre
        Optional<Livre> livreOpt = catalogueRepository.findById(livreId);

        if (etudiant != null && livreOpt.isPresent()) {
            Livre livre = livreOpt.get();
            return livre.getProgrammeEtudes().equalsIgnoreCase(etudiant.getProgrammeEtudes());
        }
        return false;
    }

    public void deleteLivre(Long id) {
        // Lancer une exception si le livre n'est pas trouvé
        if (!catalogueRepository.existsById(id)) {
            throw new LivreNotFoundException(id);
        }
        catalogueRepository.deleteById(id);
    }
}
