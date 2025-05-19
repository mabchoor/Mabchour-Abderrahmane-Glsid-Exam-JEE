package com.example.demo.Web;

import com.example.demo.entities.CreditPersonnel;
import com.example.demo.repository.CreditPersonnelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/creditpersonnel")
public class CreditPersonnelController {
    @Autowired
    private CreditPersonnelRepository creditPersonnelRepository;

    // Get all CreditPersonnel
    @GetMapping
    public List<CreditPersonnel> getAllCreditPersonnel() {
        return creditPersonnelRepository.findAll();
    }

    // Get CreditPersonnel by ID
    @GetMapping("/{id}")
    public ResponseEntity<CreditPersonnel> getCreditPersonnelById(@PathVariable Long id) {
        return creditPersonnelRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create new CreditPersonnel
    @PostMapping
    public CreditPersonnel createCreditPersonnel(@RequestBody CreditPersonnel creditPersonnel) {
        return creditPersonnelRepository.save(creditPersonnel);
    }

    // Update CreditPersonnel
    @PutMapping("/{id}")
    public ResponseEntity<CreditPersonnel> updateCreditPersonnel(@PathVariable Long id, @RequestBody CreditPersonnel details) {
        Optional<CreditPersonnel> optional = creditPersonnelRepository.findById(id);
        if (optional.isEmpty()) return ResponseEntity.notFound().build();
        CreditPersonnel cp = optional.get();
        cp.setDateDemande(details.getDateDemande());
        cp.setStatut(details.getStatut());
        cp.setDateAcceptation(details.getDateAcceptation());
        cp.setMontant(details.getMontant());
        cp.setDureeRemboursement(details.getDureeRemboursement());
        cp.setTauxInteret(details.getTauxInteret());
        cp.setMotif(details.getMotif());
        return ResponseEntity.ok(creditPersonnelRepository.save(cp));
    }

    // Delete CreditPersonnel
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCreditPersonnel(@PathVariable Long id) {
        if (!creditPersonnelRepository.existsById(id)) return ResponseEntity.notFound().build();
        creditPersonnelRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
} 