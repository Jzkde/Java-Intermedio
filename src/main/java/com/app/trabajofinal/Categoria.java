/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.trabajofinal;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
// Defines the name of the table created for the entity
@Table(name = "Categoria")
public class Categoria {
    @Id
    @Column(name = "id_categoria", unique = true)
    private int id_categoria;
    @Column(name = "desc_categoria", nullable = false)
    private String desc_categoria;

    @OneToOne(mappedBy = "id_categoria",  fetch = FetchType.EAGER)
    private Incidencia incidencia;
  

}
