
package com.app.trabajofinal;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter @Setter
// Defines the name of the table created for the entity
@Table(name = "Incidencia")
public class Incidencia {

    @Id
    @Column(name = "id_incidencia", unique = true)
    private int id_incidencia;
    @Column(name = "desc_incidencia", nullable = false)
    private String desc_incidencia;
    @Column(name = "costo", nullable = false)
    private float costo;
    @Column(name = "fecha_incidencia", nullable = false)
    private Date fecha_incidencia;
    @Column(name = "estado", nullable = false)
    private boolean estado;

    @JoinColumn(name = "id_categoria")
    @OneToOne(fetch = FetchType.EAGER)
    private Categoria id_categoria;

    @JoinColumn(name = "id_tecnico")
    @OneToOne(fetch = FetchType.EAGER)
    private Tecnico id_tecnico;

    @JoinColumn(name = "cliente")
    @ManyToOne(fetch = FetchType.EAGER)
    private Cliente dni;

}
