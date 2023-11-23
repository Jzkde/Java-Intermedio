package com.app.trabajofinal;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Cliente {
    @Id
    private int dni;
    private String nombre;
    private String direccion;
    private String mail;
    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
    private Set<Incidencia> incidencias = new HashSet<>();

    public void addIncidencia(Incidencia incidencia) {
        incidencia.setCliente(this);
        incidencias.add(incidencia);

    }


}
