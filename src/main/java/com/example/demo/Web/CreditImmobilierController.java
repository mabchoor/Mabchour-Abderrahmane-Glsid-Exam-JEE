package com.example.demo.Web;

import com.example.demo.entities.CreditImmobilier;
import com.example.demo.repository.CreditImmobilierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/creditimmobilier")
public class CreditImmobilierController {
    @Autowired
    private CreditImmobilierRepository creditImmobilierRepository;

    // Get all CreditImmobilier
    @GetMapping
    public List<CreditImmobilier> getAllCreditImmobilier() {
        return creditImmobilierRepository.findAll();
    }

    // Get CreditImmobilier by ID
    @GetMapping("/{id}")
    public ResponseEntity<CreditImmobilier> getCreditImmobilierById(@PathVariable Long id) {
        return creditImmobilierRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create new CreditImmobilier
    @PostMapping
    public CreditImmobilier createCreditImmobilier(@RequestBody CreditImmobilier creditImmobilier) {
        return creditImmobilierRepository.save(creditImmobilier);
    }

    // Update CreditImmobilier
    @PutMapping("/{id}")
    public ResponseEntity<CreditImmobilier> updateCreditImmobilier(@PathVariable Long id, @RequestBody CreditImmobilier details) {
        Optional<CreditImmobilier> optional = creditImmobilierRepository.findById(id);
        if (optional.isEmpty()) return ResponseEntity.notFound().build();
        CreditImmobilier ci = optional.get();
        ci.setDateDemande(details.getDateDemande());
        ci.setStatut(details.getStatut());
        ci.setDateAcceptation(details.getDateAcceptation());
        ci.setMontant(details.getMontant());
        ci.setDureeRemboursement(details.getDureeRemboursement());
        ci.setTauxInteret(details.getTauxInteret());
        ci.setTypeBien(details.getTypeBien());
        return ResponseEntity.ok(creditImmobilierRepository.save(ci));
    }

    // Delete CreditImmobilier
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCreditImmobilier(@PathVariable Long id) {
        if (!creditImmobilierRepository.existsById(id)) return ResponseEntity.notFound().build();
        creditImmobilierRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
} 