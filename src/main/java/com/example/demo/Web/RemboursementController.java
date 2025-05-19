package com.example.demo.Web;

import com.example.demo.entities.Remboursement;
import com.example.demo.repository.RemboursementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/remboursements")
public class RemboursementController {
    @Autowired
    private RemboursementRepository remboursementRepository;

    // Get all remboursements
    @GetMapping
    public List<Remboursement> getAllRemboursements() {
        return remboursementRepository.findAll();
    }

    // Get remboursement by ID
    @GetMapping("/{id}")
    public ResponseEntity<Remboursement> getRemboursementById(@PathVariable Long id) {
        return remboursementRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create new remboursement
    @PostMapping
    public Remboursement createRemboursement(@RequestBody Remboursement remboursement) {
        return remboursementRepository.save(remboursement);
    }

    // Update remboursement
    @PutMapping("/{id}")
    public ResponseEntity<Remboursement> updateRemboursement(@PathVariable Long id, @RequestBody Remboursement remboursementDetails) {
        Optional<Remboursement> optionalRemboursement = remboursementRepository.findById(id);
        if (optionalRemboursement.isEmpty()) return ResponseEntity.notFound().build();
        Remboursement remboursement = optionalRemboursement.get();
        remboursement.setDate(remboursementDetails.getDate());
        remboursement.setMontant(remboursementDetails.getMontant());
        remboursement.setType(remboursementDetails.getType());
        return ResponseEntity.ok(remboursementRepository.save(remboursement));
    }

    // Delete remboursement
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRemboursement(@PathVariable Long id) {
        if (!remboursementRepository.existsById(id)) return ResponseEntity.notFound().build();
        remboursementRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
} 