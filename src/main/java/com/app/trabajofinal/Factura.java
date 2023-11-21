package com.app.trabajofinal;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter @Setter
// Defines the name of the table created for the entity
@Table(name = "Factura")
public class Factura implements Serializable {

    @Id
    @Column(name = "cod_factura", unique = true)
    private int cod_factura;

    @Column(name = "desc_factura", nullable = false)
    private String descripcion_factura;

 
}
