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
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isEmpty()) return ResponseEntity.notFound().build();
        Client client = optionalClient.get();
        client.setNom(clientDetails.getNom());
        client.setEmail(clientDetails.getEmail());
        return ResponseEntity.ok(clientRepository.save(client));
    }

    // Delete client
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        if (!clientRepository.existsById(id)) return ResponseEntity.notFound().build();
        clientRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Get all credits for a client
    @GetMapping("/{id}/credits")
    public ResponseEntity<List<Credit>> getCreditsByClient(@PathVariable Long id) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(client.get().getCredits());
    }
} 