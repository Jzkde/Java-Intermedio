package com.app.trabajofinal;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
public class Tecnico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_tecnico;
    private String nombre_tecnico;

    @OneToMany(mappedBy = "tecnico", fetch = FetchType.EAGER)
    private Set<Incidencia> incidencias = new HashSet<>();

    public void addIncidencia(Incidencia incidencia) {
        incidencia.setTecnico(this);
        incidencias.add(incidencia);
    }
}

