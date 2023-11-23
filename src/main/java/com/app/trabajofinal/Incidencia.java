package com.app.trabajofinal;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Incidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_incidencia;
    private String desc_incidencia;
    private float costo;
    private LocalDate fecha_incidencia;
    private boolean estado;

    @OneToMany(mappedBy = "incidencia", fetch = FetchType.EAGER)
    private Set<Categoria> categorias = new HashSet<>();

    @OneToMany(mappedBy = "incidencia", fetch = FetchType.EAGER)
    private Set<Tecnico> tecnicos = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_dni")
    private Cliente cliente;

    public void addTecnico(Tecnico tecnico) {
        tecnico.setIncidencia(this);
        tecnicos.add(tecnico);

    }

    public void addCategoria(Categoria categoria) {
        categoria.setIncidencia(this);
        categorias.add(categoria);

    }

}
