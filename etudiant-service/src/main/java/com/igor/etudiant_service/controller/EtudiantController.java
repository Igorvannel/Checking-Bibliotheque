package com.igor.etudiant_service.controller;

import com.igor.etudiant_service.dto.EtudiantDTO;
import com.igor.etudiant_service.service.EtudiantService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/etudiants")
@AllArgsConstructor
public class EtudiantController {

    @Autowired
    private final EtudiantService etudiantService;

    @PostMapping
    public ResponseEntity<EtudiantDTO> enregistrerEtudiant(@RequestBody EtudiantDTO etudiantDTO) {
        EtudiantDTO savedEtudiant = etudiantService.enregistrerEtudiant(etudiantDTO);
        return new ResponseEntity<>(savedEtudiant, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EtudiantDTO> mettreAJourEtudiant(@PathVariable Long id, @RequestBody EtudiantDTO etudiantDTO) {
        EtudiantDTO updatedEtudiant = etudiantService.mettreAJourEtudiant(id, etudiantDTO);
        return new ResponseEntity<>(updatedEtudiant, HttpStatus.OK);
    }

    @GetMapping("/{etudiantId}")
    public ResponseEntity<EtudiantDTO> obtenirEtudiantParCode(@PathVariable Long etudiantId) {
        EtudiantDTO etudiantDTO = etudiantService.obtenirEtudiantParCode(etudiantId);
        return new ResponseEntity<>(etudiantDTO, HttpStatus.OK);
    }
}
