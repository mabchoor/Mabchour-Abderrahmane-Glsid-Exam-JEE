package com.example.demo;

import com.example.demo.entities.*;
import com.example.demo.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class Demo7Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo7Application.class, args);
    }

    @Bean
    public CommandLineRunner testRepositories(
            ClientRepository clientRepository,
            CreditRepository creditRepository,
            CreditPersonnelRepository creditPersonnelRepository,
            CreditImmobilierRepository creditImmobilierRepository,
            CreditProfessionnelRepository creditProfessionnelRepository,
            RemboursementRepository remboursementRepository
    ) {
        return args -> {
            // Create and save a client
           Client client = new Client(null, "Test User", "test@example.com", null);
            client = clientRepository.save(client);

            // Create and save CreditPersonnel
            CreditPersonnel cp = new CreditPersonnel();
            cp.setDateDemande(LocalDate.now());
            cp.setStatut(Credit.StatutCredit.EN_COURS);
            cp.setMontant(10000.0);
            cp.setDureeRemboursement(24);
            cp.setTauxInteret(3.5);
            cp.setClient(client);
            cp.setMotif("Achat voiture");
            cp = creditPersonnelRepository.save(cp);

            // Create and save CreditImmobilier
            CreditImmobilier ci = new CreditImmobilier();
            ci.setDateDemande(LocalDate.now());
            ci.setStatut(Credit.StatutCredit.ACCEPTE);
            ci.setMontant(200000.0);
            ci.setDureeRemboursement(240);
            ci.setTauxInteret(2.1);
            ci.setClient(client);
            ci.setTypeBien(CreditImmobilier.TypeBien.MAISON);
            ci = creditImmobilierRepository.save(ci);

            // Create and save CreditProfessionnel
            CreditProfessionnel cpr = new CreditProfessionnel();
            cpr.setDateDemande(LocalDate.now());
            cpr.setStatut(Credit.StatutCredit.REJETE);
            cpr.setMontant(50000.0);
            cpr.setDureeRemboursement(36);
            cpr.setTauxInteret(4.0);
            cpr.setClient(client);
            cpr.setMotif("Investissement matériel");
            cpr.setRaisonSociale("SARL Demo");
            cpr = creditProfessionnelRepository.save(cpr);

            // Create and save Remboursement for each credit
            Remboursement r1 = new Remboursement(null, LocalDate.now(), 500.0, Remboursement.TypeRemboursement.MENSUALITE, cp);
            Remboursement r2 = new Remboursement(null, LocalDate.now(), 1200.0, Remboursement.TypeRemboursement.MENSUALITE, ci);
            Remboursement r3 = new Remboursement(null, LocalDate.now(), 2000.0, Remboursement.TypeRemboursement.REMBOURSEMENT_ANTICIPE, cpr);
            remboursementRepository.saveAll(List.of(r1, r2, r3));

            // Print all clients
            System.out.println("All clients:");
            clientRepository.findAll().forEach(System.out::println);

            // Print all credits
            System.out.println("All credits:");
            creditRepository.findAll().forEach(System.out::println);

            // Print all CreditPersonnel
            System.out.println("All CreditPersonnel:");
            creditPersonnelRepository.findAll().forEach(System.out::println);

            // Print all CreditImmobilier
            System.out.println("All CreditImmobilier:");
            creditImmobilierRepository.findAll().forEach(System.out::println);

            // Print all CreditProfessionnel
            System.out.println("All CreditProfessionnel:");
            creditProfessionnelRepository.findAll().forEach(System.out::println);

            // Print all Remboursements
            System.out.println("All Remboursements:");
            remboursementRepository.findAll().forEach(System.out::println);
        };
    }
}
