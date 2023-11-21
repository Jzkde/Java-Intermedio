
package com.app.trabajofinal;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter @Setter
// Defines the name of the table created for the entity
@Table(name = "Tecnico")
public class Tecnico {
    @Id
    @Column(name = "id_tecnico", unique = true)
    private int id_tecnico;
    @Column(name = "nombre_tecnico", nullable = false)
    private String nombre_tecnico;

    @OneToOne(mappedBy = "id_tecnico",fetch = FetchType.EAGER)
    private Incidencia incidencia;

}

