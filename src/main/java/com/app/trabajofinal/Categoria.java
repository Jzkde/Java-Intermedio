/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.trabajofinal;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_categoria;
    private String desc_categoria;

    @OneToMany(mappedBy = "categoria", fetch = FetchType.EAGER)
    private Set <Incidencia> incidencias = new HashSet<>();

    public void addIncidencia(Incidencia incidencia) {
        incidencia.setCategoria(this);
        incidencias.add(incidencia);

    }

}
