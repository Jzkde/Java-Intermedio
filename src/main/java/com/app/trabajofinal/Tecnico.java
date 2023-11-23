package com.app.trabajofinal;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
public class Tecnico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_tecnico;
    private String nombre_tecnico;

    @JoinColumn(name = "incidencia_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Incidencia incidencia;

}

