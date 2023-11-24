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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria ;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tecnico_id")
    private Tecnico tecnico ;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_dni")
    private Cliente cliente;


}
