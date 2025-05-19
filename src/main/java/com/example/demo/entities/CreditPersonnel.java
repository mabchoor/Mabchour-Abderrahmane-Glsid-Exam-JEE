package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@DiscriminatorValue("PERSONNEL")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class CreditPersonnel extends Credit {
    // Getters and setters
    private String motif;

}