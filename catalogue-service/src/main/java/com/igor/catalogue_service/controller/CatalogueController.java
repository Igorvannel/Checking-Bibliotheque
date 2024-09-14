package com.igor.catalogue_service.controller;

import com.igor.catalogue_service.model.Livre;
import com.igor.catalogue_service.service.CatalogueService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livres")
@AllArgsConstructor
public class CatalogueController {

    @Autowired
    private CatalogueService catalogueService;

    // Ajouter un livre
    @PostMapping
    public Livre createLivre(@RequestBody Livre livre) {
        return catalogueService.saveLivre(livre);
    }

    @GetMapping("/verifier-programme")
    public boolean verifierLivreProgramme(@RequestParam Long etudiantId, @RequestParam Long livreId) {
        return catalogueService.verifierLivreProgramme(etudiantId, livreId);
    }

    // Obtenir un livre par ID
    @GetMapping("/{id}")
    public Livre getLivreById(@PathVariable Long id) {
        return catalogueService.getLivreById(id)
                .orElseThrow(() -> new RuntimeException("Livre non trouvé avec l'ID : " + id));
    }

    // Obtenir tous les livres
    @GetMapping
    public List<Livre> getAllLivres() {
        return catalogueService.getAllLivres();
    }

    // Obtenir les livres par programme d'études
    @GetMapping("/programme/{programmeEtudes}")
    public List<Livre> getLivresByProgramme(@PathVariable String programmeEtudes) {
        return catalogueService.getLivresByProgramme(programmeEtudes);
    }

    // Rechercher des livres par titre
    @GetMapping("/search")
    public List<Livre> searchLivres(@RequestParam String titre) {
        return catalogueService.searchLivres(titre);
    }

    // Supprimer un livre par ID
    @DeleteMapping("/{id}")
    public void deleteLivre(@PathVariable Long id) {
        catalogueService.deleteLivre(id);
    }
}
