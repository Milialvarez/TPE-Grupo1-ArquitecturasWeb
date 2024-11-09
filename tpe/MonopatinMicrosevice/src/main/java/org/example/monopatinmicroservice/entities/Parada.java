package org.example.monopatinmicroservice.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Parada {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(mappedBy = "parada")
    @JoinColumn(nullable = true)
    @JsonBackReference
    private Monopatin monopatin;

    public boolean isHabilitada() {
        return monopatin == null;
    }
}
