package com.example.demo.Web;

import com.example.demo.entities.Credit;
import com.example.demo.entities.Remboursement;
import com.example.demo.repository.CreditRepository;
import com.example.demo.repository.RemboursementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/credits")
public class CreditController {
    @Autowired
    private CreditRepository creditRepository;
    @Autowired
    private RemboursementRepository remboursementRepository;

    // Get all credits
    @GetMapping
    public List<Credit> getAllCredits() {
        return creditRepository.findAll();
    }

    // Get credit by ID
    @GetMapping("/{id}")
    public ResponseEntity<Credit> getCreditById(@PathVariable Long id) {
        return creditRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create new credit
    @PostMapping
    public Credit createCredit(@RequestBody Credit credit) {
        return creditRepository.save(credit);
    }

    // Update credit
    @PutMapping("/{id}")
    public ResponseEntity<Credit> updateCredit(@PathVariable Long id, @RequestBody Credit creditDetails) {
        Optional<Credit> optionalCredit = creditRepository.findById(id);
        if (optionalCredit.isEmpty()) return ResponseEntity.notFound().build();
        Credit credit = optionalCredit.get();
        credit.setDateDemande(creditDetails.getDateDemande());
        credit.setStatut(creditDetails.getStatut());
        credit.setDateAcceptation(creditDetails.getDateAcceptation());
        credit.setMontant(creditDetails.getMontant());
        credit.setDureeRemboursement(creditDetails.getDureeRemboursement());
        credit.setTauxInteret(creditDetails.getTauxInteret());
        return ResponseEntity.ok(creditRepository.save(credit));
    }

    // Delete credit
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCredit(@PathVariable Long id) {
        if (!creditRepository.existsById(id)) return ResponseEntity.notFound().build();
        creditRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Get all remboursements for a credit
    @GetMapping("/{id}/remboursements")
    public ResponseEntity<List<Remboursement>> getRemboursementsByCredit(@PathVariable Long id) {
        Optional<Credit> credit = creditRepository.findById(id);
        if (credit.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(credit.get().getRemboursements());
    }
} 