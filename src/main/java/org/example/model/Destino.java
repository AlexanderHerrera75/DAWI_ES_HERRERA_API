package org.example.model;

import jakarta.persistence.*;

@Entity
public class Destino {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer idDestino;
    public String nomDestino;
    public Double preDestino;
}