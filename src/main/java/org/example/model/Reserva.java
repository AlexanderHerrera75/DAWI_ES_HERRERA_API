package org.example.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer numReserva;
    public LocalDate fchReserva;
    public String nomCliente;

    @ManyToOne
    @JoinColumn(name = "id_destino")
    public Destino destino;

    public Integer cantidad;
    public String estado;
}
