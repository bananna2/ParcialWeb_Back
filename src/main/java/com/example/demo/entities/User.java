package com.example.demo.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String identificador;

    @Column(nullable = false)
    private Double valor;

    @Column(nullable = false)
    private String nombreContratante;

    @Column(nullable = false)
    private String documentoContratante;

    @Column(nullable = false)
    private String nombreContratantista;

    @Column(nullable = false)
    private String documentoContratantista;

    @Column(nullable = false)
    private Date fechaInicial;

    @Column(nullable = false)
    private Date fechaFinal;
}