package com.app.trabajofinal;


import org.hibernate.Session;

import javax.swing.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


public class TrabajoFinal {

    String desc = JOptionPane.showInputDialog("Ingrese la Descripcion");
    float costo = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el Costo"));
    String fechaStr = JOptionPane.showInputDialog("Ingrese la fecha desde con el siguiente formato (YYYY-MM-DD)");
    Date fecha = Date.from(Instant.parse(fechaStr));
    String desdeStr = JOptionPane.showInputDialog("Ingrese la fecha desde con el siguiente formato (YYYY-MM-DD)");
    String hastaStr = JOptionPane.showInputDialog("Ingrese la fecha hasta con el siguiente formato (YYYY-MM-DD)");

//    Date desde = Date.from(Instant.parse(desdeStr));
//    Date hasta = Date.from(Instant.parse(hastaStr));
    Date desde = Date.from(LocalDate.parse(desdeStr).atStartOfDay(ZoneId.systemDefault()).toInstant());
    Date hasta = Date.from(LocalDate.parse(hastaStr).atStartOfDay(ZoneId.systemDefault()).toInstant());


    public static void main(String[] args) {


        TrabajoFinal es = new TrabajoFinal();

        es.ejemplo2();
    }


    public void ejemplo2() {
        createAndStoreEvent("El Event");
        listas();
       // listEvents();
        HibernateUtil.getSessionFactory().close();
    }

    private void createAndStoreEvent(String title) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();


        Incidencia inc = new Incidencia();

        inc.setDesc_incidencia(desc);
        inc.setCosto(costo);
        inc.setFecha_incidencia(fecha);
        inc.setEstado(true);
//        inc.setId_categoria(categoria);
//        inc.setId_tecnico(tecnico);

        session.save(inc);

        session.getTransaction().commit();

    }

    private static void listas() {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Cliente> cli = (List<Cliente>) session.createQuery("from Incidencia").list();
        List<Tecnico> tec = (List<Tecnico>) session.createQuery("from Tecnico").list();
        List<Categoria> categ = (List<Categoria>) session.createQuery("from Categoria").list();
        session.getTransaction().commit();

        String tecnicoLista = tec.stream().map(tec1 -> tec1.getId_tecnico() + " - " + tec1.getNombre_tecnico()).collect(Collectors.joining("\n"));
        String tecnico = JOptionPane.showInputDialog("Seleccione un Técnico de la lista:\n" + tecnicoLista);

    }

//    private void listEvents() {
//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        session.beginTransaction();
//        List<Incidencia> result = (List<Incidencia>) session.createQuery("from Incidencia").list();
//        session.getTransaction().commit();
//        List<Incidencia> filteredList = result.stream().filter(incidencia -> incidencia.getFecha_incidencia().after(desde) && incidencia.getFecha_incidencia().before(hasta))
//                .collect(Collectors.toList());
//        for (Incidencia e : result) {
//            System.out.println("Descripcion: " + e.getDesc_incidencia() + "/ Fecha: " + e.getFecha_incidencia() + "/ Costo: "
//                    + e.getCosto() + "/ Categoria: " + e.getId_categoria().getDesc_categoria() + "/ Tecnico: "
//                    + e.getId_tecnico().getNombre_tecnico() + "/ Cliente: " + e.getDni().getNombre());
//
//        }
//    }
}