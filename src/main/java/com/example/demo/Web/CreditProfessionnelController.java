package com.example.demo.Web;

import com.example.demo.entities.CreditProfessionnel;
import com.example.demo.repository.CreditProfessionnelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/creditprofessionnel")
public class CreditProfessionnelController {
    @Autowired
    private CreditProfessionnelRepository creditProfessionnelRepository;

    // Get all CreditProfessionnel
    @GetMapping
    public List<CreditProfessionnel> getAllCreditProfessionnel() {
        return creditProfessionnelRepository.findAll();
    }

    // Get CreditProfessionnel by ID
    @GetMapping("/{id}")
    public ResponseEntity<CreditProfessionnel> getCreditProfessionnelById(@PathVariable Long id) {
        return creditProfessionnelRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create new CreditProfessionnel
    @PostMapping
    public CreditProfessionnel createCreditProfessionnel(@RequestBody CreditProfessionnel creditProfessionnel) {
        return creditProfessionnelRepository.save(creditProfessionnel);
    }

    // Update CreditProfessionnel
    @PutMapping("/{id}")
    public ResponseEntity<CreditProfessionnel> updateCreditProfessionnel(@PathVariable Long id, @RequestBody CreditProfessionnel details) {
        Optional<CreditProfessionnel> optional = creditProfessionnelRepository.findById(id);
        if (optional.isEmpty()) return ResponseEntity.notFound().build();
        CreditProfessionnel cp = optional.get();
        cp.setDateDemande(details.getDateDemande());
        cp.setStatut(details.getStatut());
        cp.setDateAcceptation(details.getDateAcceptation());
        cp.setMontant(details.getMontant());
        cp.setDureeRemboursement(details.getDureeRemboursement());
        cp.setTauxInteret(details.getTauxInteret());
        cp.setMotif(details.getMotif());
        cp.setRaisonSociale(details.getRaisonSociale());
        return ResponseEntity.ok(creditProfessionnelRepository.save(cp));
    }

    // Delete CreditProfessionnel
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCreditProfessionnel(@PathVariable Long id) {
        if (!creditProfessionnelRepository.existsById(id)) return ResponseEntity.notFound().build();
        creditProfessionnelRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
} 