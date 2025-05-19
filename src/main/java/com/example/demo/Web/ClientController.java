package com.example.demo.Web;

import com.example.demo.entities.Client;
import com.example.demo.entities.Credit;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clients")
@CrossOrigin(origins = "http://localhost:4200")
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private CreditRepository creditRepository;

    // Get all clients
    @GetMapping
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    // Get client by ID
    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        return clientRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create new client
    @PostMapping
    public Client createClient(@RequestBody Client client) {
        return clientRepository.save(client);
    }

    // Update client
    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client clientDetails) {
        return clientRepository.findById(id)
                .map(existingClient -> {
                    existingClient.setNom(clientDetails.getNom());
                    existingClient.setPrenom(clientDetails.getPrenom());
                    existingClient.setEmail(clientDetails.getEmail());
                    existingClient.setTelephone(clientDetails.getTelephone());
                    existingClient.setAdresse(clientDetails.getAdresse());
                    existingClient.setDateNaissance(clientDetails.getDateNaissance());
                    existingClient.setProfession(clientDetails.getProfession());
                    existingClient.setRevenuMensuel(clientDetails.getRevenuMensuel());
                    return ResponseEntity.ok(clientRepository.save(existingClient));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete client
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        return clientRepository.findById(id)
                .map(client -> {
                    clientRepository.delete(client);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Get all credits for a client
    @GetMapping("/{id}/credits")
    public ResponseEntity<List<Credit>> getCreditsByClient(@PathVariable Long id) {
        return clientRepository.findById(id)
                .map(client -> ResponseEntity.ok(client.getCredits()))
                .orElse(ResponseEntity.notFound().build());
    }
} 