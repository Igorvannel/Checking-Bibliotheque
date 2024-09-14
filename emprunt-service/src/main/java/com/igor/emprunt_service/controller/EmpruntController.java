package com.igor.emprunt_service.controller;

import com.igor.emprunt_service.service.EmpruntService;
import com.igor.emprunt_service.dto.EmpruntDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/emprunts")
public class EmpruntController {
    @Autowired
    private final EmpruntService empruntService;


    @PostMapping
    public EmpruntDTO creerEmprunt(@RequestBody EmpruntDTO empruntDTO) {
        return empruntService.creerEmprunt(empruntDTO);
    }

    @GetMapping("/{id}")
    public EmpruntDTO obtenirEmprunt(@PathVariable Long id) {
        return empruntService.obtenirEmprunt(id);
    }

    @GetMapping("/etudiant/{etudiantId}")
    public List<EmpruntDTO> obtenirEmpruntsParEtudiant(@PathVariable Long etudiantId) {
        return empruntService.obtenirEmpruntsParEtudiant(etudiantId);
    }

    @PutMapping("/{id}/retour")
    public EmpruntDTO retournerLivre(@PathVariable Long id) {
        return empruntService.retournerLivre(id);
    }

    @GetMapping("/verifier")
    public Boolean verifierEligibiliteEmprunt(
            @RequestParam Long etudiantId,
            @RequestParam Long livreId) {
        return empruntService.verifierEligibiliteEmprunt(etudiantId, livreId);
    }

    @GetMapping("/en-retard")
    public List<EmpruntDTO> obtenirEmpruntsEnRetard() {
        return empruntService.obtenirEmpruntsEnRetard();
    }
}
