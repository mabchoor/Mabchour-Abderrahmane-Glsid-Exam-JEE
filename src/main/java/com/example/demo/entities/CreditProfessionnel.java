package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@DiscriminatorValue("PROFESSIONNEL")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditProfessionnel extends Credit {
    // Getters and setters
    private String motif;
    private String raisonSociale;

}