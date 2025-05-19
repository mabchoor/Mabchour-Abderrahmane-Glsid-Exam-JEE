package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@DiscriminatorValue("IMMOBILIER")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class CreditImmobilier extends Credit {
    // Getters and setters
    @Enumerated(EnumType.STRING)
    private TypeBien typeBien;

    public enum TypeBien {
        APPARTEMENT,
        MAISON,
        LOCAL_COMMERCIAL
    }

}