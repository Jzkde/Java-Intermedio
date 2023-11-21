package com.app.trabajofinal;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter
// Defines the name of the table created for the entity
@Table(name = "Cliente")
public class Cliente {
    @Id
    @Column(name = "dni", unique = true)
    private int dni;
    @Column(name = "nombre", nullable = false)
    private String nombre;
    @Column(name = "direccion", nullable = false)
    private String direccion;
    @Column(name = "mail", nullable = false)
    private String mail;
    @OneToMany(mappedBy = "dni", fetch = FetchType.EAGER)
    @Column(name = "dni", nullable = false)
    private Set<Incidencia> listaIncidencias = new HashSet<>();






}
